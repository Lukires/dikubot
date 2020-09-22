package com.diku.ticket.tickets;

import com.diku.ku.Major;
import com.diku.main.Main;
import com.diku.ticket.Ticket;
import com.diku.ticket.TicketDisplay;
import com.diku.ticket.ticketactions.AcceptMajorTicketAction;
import com.diku.ticket.ticketactions.RejectMajorTicketAction;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.UUID;

public class MajorTicket extends Ticket {


    private Major major;

    public MajorTicket(Guild guild, User user, java.util.UUID UUID, Major major) {
        super(guild, user, UUID);
        this.major=major;
    }

    @Override
    public Type getType() {
        return Type.MAJOR;
    }

    public Major getMajor() {
        return this.major;
    }

    public String getContext() {
        return major.name();
    }

    @Override
    public MessageChannel getClosedTicketChannel() {
        return guild.getTextChannelById("756470614323101736");
    }

    @Override
    public MessageChannel getOpenTicketChannel() {
        return guild.getTextChannelById("756603513177374771");
    }

    @Override
    public TicketDisplay getDisplay() {
        TicketDisplay.Builder builder = new TicketDisplay.Builder();
        builder.addAction(Main.jda.getEmotesByName(":white_check_mark:", true).get(0), new AcceptMajorTicketAction());
        builder.addAction(Main.jda.getEmotesByName(":x:", true).get(0), new RejectMajorTicketAction());
        builder.setUser(getUser());

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsTag()).append(" ").append(user.getName()).append(" har anmodet at deres !major bliver sat til ").append(getMajor().getRole().getRole());
        mb.append("\nAccepter: ").append(String.valueOf(Main.jda.getEmotesByName("x", true).get(0))).append(" Afvis: ").append(String.valueOf(Main.jda.getEmotesByName("x", true).get(0)));
        builder.setMessage(mb.build());
        return builder.build();
    }
}
