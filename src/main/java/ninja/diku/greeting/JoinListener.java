package ninja.diku.greeting;

import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

    //When a user joins a server
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member member = e.getMember();
        member.getUser().openPrivateChannel().queue((channel) ->
        {
            MessageCreateBuilder mb = new MessageCreateBuilder();
            mb.addContent(member.getAsMention()).addContent(" hej og velkommen til DIKU's uofficielle Discord Server!\n");
            mb.addContent("For at få adgang til hele vores Discord server, kan du bruge kommandoen **!join [ku-email] [fulde navn]**\n");
            mb.addContent("Du bedes også ændre dit navn på Discord serveren til dit **fulde navn.**\n");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(member.getUser(), e.getGuild()));
        });
        e.getGuild().addRoleToMember(member, e.getGuild().getRolesByName("Guest", true).get(0)).queue();
    }

}
