package ninja.diku.ticket.ticketactions;

import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.conversation.GuildConversation;
import ninja.diku.ticket.TicketAction;
import ninja.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class AcceptMajorTicketAction implements TicketAction<MajorTicket> {
    @Override
    public void execute(MajorTicket ticket, User executedBy) {
        User target = ticket.getUser();
        Guild guild = ticket.getGuild();
        guild.removeRoleFromMember(target, guild.getRolesByName("KU", true).get(0)).queue();
        guild.addRoleToMember(target, guild.getRolesByName(ticket.getMajor().getRole().getRole(), true).get(0)).queue();

        target.openPrivateChannel().queue((channel) ->
        {
            MessageCreateBuilder mb = new MessageCreateBuilder();
            mb.addContent(target.getAsMention()).addContent(" din anmodning om !major ").addContent(ticket.getMajor().getName()).addContent(" er blevet accepteret");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(target, guild));
        });
        ticket.close(":white_check_mark: ACCEPTERET");
    }

    @Override
    public String command() {
        return "accept";
    }
}
