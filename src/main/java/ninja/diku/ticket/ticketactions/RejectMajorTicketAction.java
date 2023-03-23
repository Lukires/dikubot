package ninja.diku.ticket.ticketactions;

import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.conversation.GuildConversation;
import ninja.diku.ticket.TicketAction;
import ninja.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class RejectMajorTicketAction implements TicketAction<MajorTicket> {
    @Override
    public void execute(MajorTicket ticket, User executedBy) {
        User target = ticket.getUser();
        Guild guild = ticket.getGuild();

        target.openPrivateChannel().queue((channel) ->
        {
            MessageCreateBuilder mb = new MessageCreateBuilder();
            mb.addContent(target.getAsMention()).addContent(" din anmodning om !major ").addContent(ticket.getMajor().getName()).addContent(" er blevet afvist");
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
