package com.diku.models;

import com.diku.database.Collections;
import com.diku.main.Main;
import com.diku.models.exceptions.TicketNotFoundException;
import com.diku.ticket.Ticket;
import com.diku.ticket.TicketFactory;
import com.diku.ticket.tickets.GenericTicket;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.UUID;

public class TicketModel extends Model<Ticket> {
    /**
     * @param ticket
     */
    public TicketModel(Ticket ticket) {
        super(ticket);
    }

    public static TicketModel getTicketModel(UUID uuid) throws TicketNotFoundException {
        Document document = Collections.TICKETS.getCollection().find(Filters.eq("_id", uuid.toString())).first();
        if (document == null) {
            throw new TicketNotFoundException(uuid.toString());
        }

        //This is hacky and dumb, but I couldn't be bothered to do it properly right now
        return new TicketModel(new GenericTicket(Main.jda.getGuildById(document.getString("guild")), Main.jda.getUserById(document.getString("user")), uuid));
    }

    public static TicketModel getTicketModelByMessageID(String messageID) throws TicketNotFoundException {
        System.out.println(messageID);
        Document document = Collections.TICKETS.getCollection().find(Filters.eq("message", messageID)).first();
        if(document == null) {
            System.out.println("oof");
            throw new TicketNotFoundException(messageID);
        }
        return getTicketModel(UUID.fromString(document.getString("_id")));
    }

    @Override
    protected Document init() {
        Document document = new Document();
        document.append("_id", getID());
        document.append("user", object.getUser().getId());
        document.append("type", object.getType().name());
        document.append("context", object.getContext());
        document.append("open", true);
        document.append("guild", object.getGuild().getId());
        document.append("message", "");
        return document;
    }

    public void setMessage(Message message) {
        System.out.println(message.getId());
        update("message", message.getId());
    }

    public Message getMessage() {
        MessageChannel channel = isOpen()?object.getOpenTicketChannel():object.getClosedTicketChannel();
        return channel.retrieveMessageById(document.getString("message")).complete();
    }

    public User getUser() {
        return Main.jda.retrieveUserById(document.getString("user")).complete();
    }

    public Ticket.Type getType() {
        return Ticket.Type.valueOf(document.getString("type"));
    }

    public String getContext() {
        return document.getString("context");
    }

    public Guild getGuild() {
        return Main.jda.getGuildById(document.getString("guild"));
    }

    public boolean isOpen() {
        return document.getBoolean("open");
    }

    public void setOpen(boolean open) {
        update("open", open);
    }

    public Ticket getTicket() {
        return TicketFactory.getTicket(this);
    }

    @Override
    public final MongoCollection<Document> getCollection() {
        return Collections.TICKETS.getCollection();
    }

    @Override
    public String getID() {
        return object.getUUID().toString();
    }
}
