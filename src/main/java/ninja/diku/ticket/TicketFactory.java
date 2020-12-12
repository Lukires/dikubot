package ninja.diku.ticket;

import ninja.diku.ku.Major;
import ninja.diku.models.TicketModel;
import ninja.diku.ticket.tickets.GenericTicket;
import ninja.diku.ticket.tickets.MajorTicket;

import java.util.UUID;

public class TicketFactory {

    public static Ticket getTicket(TicketModel model) {
        Ticket.Type type = model.getType();

        if(type.equals(Ticket.Type.MAJOR)) {
            return new MajorTicket(model.getGuild(), model.getUser(), UUID.fromString(model.getID()), Major.valueOf(model.getContext()));
        }
        return new GenericTicket(model.getGuild(), model.getUser(), UUID.fromString(model.getID()));
    }
}
