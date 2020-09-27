package com.diku.ticket;

import com.diku.main.Util;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TicketListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        User user = e.getUser();
        Guild guild = e.getGuild();

        if(!Util.isMod(user, guild)) {
            return;
        }

        e.getChannel().sendMessage("toString " + e.getReaction().toString()).queue();


    }
}
