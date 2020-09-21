package com.diku.ticket;

import com.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
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
    /*public Ticket(User user) {
        this(user, java.util.UUID.randomUUID());
    }*/
    public Ticket(User user, UUID UUID) {
        this.UUID = UUID;
        this.user=user;
    }

    public static Ticket getTicket(UUID uuid) {
        return null;
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

}