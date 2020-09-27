package com.diku.ticket;

import net.dv8tion.jda.api.entities.User;

public interface TicketAction {
    public void execute(Ticket ticket, User executedBy);
    public String command();
}