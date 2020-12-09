package com.diku.ticket.ticketactions;

import com.diku.conversation.GuildConversation;
import com.diku.ticket.Ticket;
import com.diku.ticket.TicketAction;
import com.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class RejectMajorTicketAction implements TicketAction<MajorTicket> {
    @Override
    public void execute(MajorTicket ticket, User executedBy) {
        User target = ticket.getUser();
        Guild guild = ticket.getGuild();

        target.openPrivateChannel().queue((channel) ->
        {
            MessageBuilder mb = new MessageBuilder();
            mb.append(target.getAsMention()).append(" din anmodning om !major ").append(ticket.getMajor().getName()).append(" er blevet afvist");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(target, guild));
        });

        ticket.close(":x: AFVIST");
    }

    @Override
    public String command() {
        return "reject";
    }
}
