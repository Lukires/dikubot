package ninja.diku.ticket.tickets;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import ninja.diku.ticket.Ticket;
import ninja.diku.ticket.TicketDisplay;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class GenericTicket extends Ticket {
    public GenericTicket(Guild guild, User user, java.util.UUID UUID) {
        super(guild, user, UUID);
    }

    @Override
    public Type getType() {
        return Type.GENERIC;
    }

    @Override
    public TicketDisplay getDisplay() {
        return null;
    }

    @Override
    public TicketDisplay getCloseDisplay() {
        return null;
    }

    @Override
    public String getContext() {
        return "";
    }

    @Override
    public TextChannel getClosedTicketChannel() {
        return guild.getTextChannelById("756470614323101736");
    }

    @Override
    public TextChannel getOpenTicketChannel() {
        return guild.getTextChannelById("756603513177374771");
    }
}
