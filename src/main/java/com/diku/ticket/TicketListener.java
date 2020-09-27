package com.diku.ticket;

import com.diku.main.Main;
import com.diku.main.Util;
import com.diku.models.TicketModel;
import com.diku.models.exceptions.TicketNotFoundException;
import com.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class TicketListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        User user = e.getUser();
        Guild guild = e.getGuild();

        if(!Util.isMod(user, guild)) {
            return;
        }

        if (!Main.jda.getSelfUser().getId().equals(user.getId())) {
            return;
        }

        TicketModel ticketModel;
        try {
            ticketModel = TicketModel.getTicketModelByMessageID(e.getMessageId());
        } catch (TicketNotFoundException ticketNotFoundException) {
            return;
        }

        Ticket ticket = ticketModel.getTicket();

        if (!(ticket instanceof MajorTicket)) {
            return;
        }

        HashMap<String, TicketAction> actions = ticket.getDisplay().getActions();
        String clicked = e.getReactionEmote().getAsCodepoints();

        if(!actions.containsKey(clicked)) {
            return;
        }

        actions.get(clicked).execute(ticket, user);


    }
}
