package com.diku.greeting;

import com.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

    //When a user joins a server
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        System.out.println(e.getUser().getId());
        Member member = e.getMember();
        member.getUser().openPrivateChannel().queue((channel) ->
        {
            System.out.println(channel.getId());
            MessageBuilder mb = new MessageBuilder();
            mb.append(member.getAsMention()).append(" hej og velkommen til DIKU's uofficielle Discord Server!\n");
            mb.append("For at få adgang til hele vores Discord server, kan du bruge kommandoen **!role**");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(member.getUser(), channel, e.getGuild()));
        });
    }

}
