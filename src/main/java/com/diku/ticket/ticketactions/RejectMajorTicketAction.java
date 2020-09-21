package com.diku.ticket.ticketactions;

import com.diku.ticket.Ticket;
import com.diku.ticket.TicketAction;
import net.dv8tion.jda.api.entities.User;

public class RejectMajorTicketAction implements TicketAction {
    @Override
    public void click(Ticket ticket, User clickedBy) {

    }

    @Override
    public String command() {
        return "reject";
    }
}
