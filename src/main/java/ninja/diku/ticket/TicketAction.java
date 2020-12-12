package ninja.diku.ticket;

import net.dv8tion.jda.api.entities.User;

public interface TicketAction<T extends Ticket> {
    public void execute(T ticket, User executedBy);
    public String command();
}