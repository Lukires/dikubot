package com.diku.ticket.tickets;

import com.diku.ku.Major;
import com.diku.main.Main;
import com.diku.ticket.Ticket;
import com.diku.ticket.ticketactions.AcceptMajorTicketAction;
import com.diku.ticket.ticketactions.RejectMajorTicketAction;
import net.dv8tion.jda.api.entities.User;

public class MajorTicket extends Ticket {
    public MajorTicket(User user, Major major) {
        super(user);
    }

    @Override
    protected void build() {
        add(Main.jda.getEmotesByName("white_check_mark", true).get(0), new AcceptMajorTicketAction());
        add(Main.jda.getEmotesByName("x", true).get(0), new RejectMajorTicketAction());
    }
}
