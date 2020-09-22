package com.diku.ticket;

import com.diku.main.Main;
import com.diku.models.TicketModel;
import com.diku.models.exceptions.TicketNotFoundException;
import com.diku.ticket.exceptions.MessageNotFoundException;
import com.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public abstract class Ticket {


    public enum Type {

        MAJOR(MajorTicket.class), GENERIC(Ticket.class);

        final Class<? extends Ticket> ticket;
        Type(Class<? extends Ticket> ticket) {
            this.ticket=ticket;
        }

        public Class<? extends Ticket> getTicket() {
            return ticket;
        }
    }


    protected UUID UUID;
    protected User user;
    protected Guild guild;
    /*public Ticket(User user) {
        this(user, java.util.UUID.randomUUID());
    }*/
    public Ticket(Guild guild, User user, UUID UUID) {
        this.UUID = UUID;
        this.user=user;
        this.guild=guild;
    }

    public static Ticket getTicket(UUID uuid) throws TicketNotFoundException {
        return TicketFactory.getTicket(TicketModel.getTicketModel(uuid));
    }

    public static Message getMessageFromUUID(UUID uuid) throws TicketNotFoundException, MessageNotFoundException {
        MessagePaginationAction messages = getTicket(uuid).getOpenTicketChannel().getIterableHistory();
        for(Message message : messages) {
            if(!Objects.requireNonNull(message.getMember()).getId().equals("742743929459572766")) {
                continue;
            }
            String content = message.getContentRaw();

            if (!content.startsWith("UUID: "+uuid.toString())) {
                continue;
            }
            return message;
        }
        throw new MessageNotFoundException();
    }

    public Guild getGuild() {
        return guild;
    }

    public UUID getUUID() {
        return this.UUID;
    }
    public User getUser() {
        return user;
    }
    abstract public Type getType();
    abstract public TicketDisplay getDisplay();
    abstract public String getContext();
    abstract public MessageChannel getOpenTicketChannel();
    abstract public MessageChannel getClosedTicketChannel();

    public void activate() {
        TicketDisplay display = getDisplay();
        TicketModel ticketModel = new TicketModel(this);
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("UUID: ").append(getUUID().toString()).append("\n");
        messageBuilder.append(display.getMessage().getContentRaw());
        Message message = messageBuilder.build();

        getOpenTicketChannel().sendMessage(message).queue((sentMessage) ->
        {
            for(String emote : display.getActions().keySet()) {
                sentMessage.addReaction(emote).queue();
            }

        });

    }


}