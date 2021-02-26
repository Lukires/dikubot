package ninja.diku.command.commands.fun;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.Random;

public class QuoteCommand implements Command {

    /**
     * This command will return a random qoute from the qoute channel.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */

    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        TextChannel quoteChannel = guild.getTextChannelById("753287293606821913");
        if (guild.getId().toString() == "758330864651862066") {
            quoteChannel = guild.getTextChannelById("789268505152716831");
        } 
        if(quoteChannel == null) {
            if(quoteChannel == null) {
                channel.sendMessage("There seems to be an error, contact a Moderator!").queue();
                return;
            }
        }
        MessageHistory.getHistoryFromBeginning(quoteChannel).queue(messageHistory -> {
            List<Message> quotes = messageHistory.getRetrievedHistory();
            int number = new Random().nextInt(quotes.size()-1);
            channel.sendMessage(quotes.get(number)).queue();
        });
    }

    @Override
    public String getDescription() {
        return "Giver dig et tilfældigt citat fra #citater";
    }

    @Override
    public String getUsage() {
        return "!citat eller !quote";
    }
}
