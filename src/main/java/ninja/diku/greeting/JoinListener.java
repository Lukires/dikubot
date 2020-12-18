package ninja.diku.greeting;

import ninja.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

    /**
     * We send a welcome message with instructions to the user when they join the discord server.
     * @param e GuildMemberJoiNEvent is triggered upon a member joining the guild
     * @return void
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member member = e.getMember();
        member.getUser().openPrivateChannel().queue((channel) ->
        {
            MessageBuilder mb = new MessageBuilder();
            mb.append(member.getAsMention()).append(" hej og velkommen til DIKU's uofficielle Discord Server!\n");
            mb.append("For at få adgang til hele vores Discord server, kan du bruge kommandoen **!join [ku-email] [fulde navn]**\n");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(member.getUser(), e.getGuild()));
        });
        e.getGuild().addRoleToMember(member, e.getGuild().getRolesByName("Guest", true).get(0)).queue();
    }

}
