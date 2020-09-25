package com.diku.greeting;

import com.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {



    //When the bots join a server
    public void onGuildJoin(GuildJoinEvent e) {

    }

    //When a user joins a server
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        User user = e.getUser();
        user.openPrivateChannel().queue((channel) ->
        {
            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()+" hej og velkommen til DIKU 2020's Discord Server!\n");
            mb.append("For at få adgang til hele vores Discord server, kan du bruge kommandoen **!role**");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(user, channel, e.getGuild()));
        });
    }

}
