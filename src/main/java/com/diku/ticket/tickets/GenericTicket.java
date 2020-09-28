package com.diku.ticket.tickets;

import com.diku.ticket.Ticket;
import com.diku.ticket.TicketDisplay;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.UUID;

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
    public MessageChannel getClosedTicketChannel() {
        return guild.getTextChannelById("756470614323101736");
    }

    @Override
    public MessageChannel getOpenTicketChannel() {
        return guild.getTextChannelById("756603513177374771");
    }
}
