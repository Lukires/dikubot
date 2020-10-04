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
    abstract public TicketDisplay getCloseDisplay();
    abstract public String getContext();
    abstract public MessageChannel getOpenTicketChannel();
    abstract public MessageChannel getClosedTicketChannel();

    public void close(String comment) {
        TicketModel ticketModel = new TicketModel(this);
        String messageID = ticketModel.getMessageId();
        ticketModel.setOpen(false);

        MessageChannel channel = getOpenTicketChannel();
        channel.retrieveMessageById(messageID).queue((message -> {
            message.delete().queue();
        }));

        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("==========================================\n");
        messageBuilder.append(comment).append("\n");
        messageBuilder.append(getCloseDisplay().getMessage().getContentRaw()).append("\n");
        messageBuilder.append("==========================================");
        getClosedTicketChannel().sendMessage(messageBuilder.build()).queue();
    }

    public void activate() {
        TicketDisplay display = getDisplay();
        TicketModel ticketModel = new TicketModel(this);
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append(display.getMessage().getContentRaw());
        Message message = messageBuilder.build();

        getOpenTicketChannel().sendMessage(message).queue((sentMessage) ->
        {
            for(String emote : display.getActions().keySet()) {
                sentMessage.addReaction(emote).queue();
            }
            ticketModel.setMessage(sentMessage);
        });

    }


}