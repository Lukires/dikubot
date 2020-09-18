package com.diku.ticket;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public abstract class Ticket {

    private HashMap<Emote, TicketAction> actions = new HashMap<Emote, TicketAction>();

    private User user;
    public Ticket(User user) {
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    protected void add(Emote emote, TicketAction action) {
        actions.put(emote, action);
    }
    protected abstract void build();
}
