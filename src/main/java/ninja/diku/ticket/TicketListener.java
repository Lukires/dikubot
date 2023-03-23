package ninja.diku.ticket;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import ninja.diku.main.Main;
import ninja.diku.main.Util;
import ninja.diku.models.TicketModel;
import ninja.diku.models.exceptions.TicketNotFoundException;
import ninja.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class TicketListener extends ListenerAdapter {

    public void onGuildMessageReactionAdd(MessageReactionAddEvent e) {
        User user = e.getUser();
        Guild guild = e.getGuild();

        if(!Util.isMod(user, guild)) {
            return;
        }

        if (Main.jda.getSelfUser().getId().equals(user.getId())) {
            return;
        }

        TicketModel ticketModel;
        try {
            ticketModel = TicketModel.getTicketModelByMessageID(e.getMessageId());
        } catch (TicketNotFoundException ticketNotFoundException) {
            return;
        }

        if (!ticketModel.isOpen()) {
            return;
        }

        Ticket ticket = ticketModel.getTicket();

        if (!(ticket instanceof MajorTicket)) {
            return;
        }

        HashMap<String, TicketAction> actions = ticket.getDisplay().getActions();
        String clicked = e.getEmoji().getAsReactionCode();

        if(!actions.containsKey(clicked)) {
            return;
        }

        actions.get(clicked).execute(ticket, user);


    }
}
