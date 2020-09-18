package com.diku.ticket;

import net.dv8tion.jda.api.entities.User;

abstract public class TicketAction {
    public abstract void click(Ticket ticket, User clickedBy);
}
