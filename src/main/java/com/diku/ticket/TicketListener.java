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

        e.getChannel().sendMessage("ID "+e.getReactionEmote().getId()).queue();
        e.getChannel().sendMessage("toString " + e.getReaction().toString()).queue();
        e.getChannel().sendMessage("asMention " + e.getReactionEmote().getEmote().getAsMention()).queue();
        e.getChannel().sendMessage("name "+e.getReactionEmote().getName()).queue();
        if(!Util.isMod(user, guild)) {
            return;
        }



    }
}
