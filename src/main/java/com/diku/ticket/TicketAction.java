package com.diku.ticket;

import net.dv8tion.jda.api.entities.User;

public interface TicketAction {
    public void click(Ticket ticket, User clickedBy);
    public String command();
}