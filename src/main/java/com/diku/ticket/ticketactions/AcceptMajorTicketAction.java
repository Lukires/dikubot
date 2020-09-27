package com.diku.ticket.ticketactions;

import com.diku.ticket.Ticket;
import com.diku.ticket.TicketAction;
import net.dv8tion.jda.api.entities.User;

public class AcceptMajorTicketAction implements TicketAction {
    @Override
    public void execute(Ticket ticket, User executedBy) {
        System.out.println("success!");
    }

    @Override
    public String command() {
        return "accept";
    }
}
