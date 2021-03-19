package ninja.diku.command.commands.fun;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.*;

import java.util.HashMap;
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
            channel.sendMessage(pickMessage(quotes)).queue();
        });
    }

    Random random = new Random();
    public static HashMap<Message, Integer> lastSeen = new HashMap<Message, Integer>();
    public static int current = 0;
    private final static int rerollChance = 99;

    private Message pickMessage(List<Message> messages) {
        int index = random.nextInt(messages.size()-1);
        Message message = messages.get(index);

        int emotes = message.getReactions().stream().mapToInt(value -> value.getCount()).sum();

        if (random.nextInt(101) - (lastSeen.containsKey(message) ? (100 - current - lastSeen.get(message)) : 0)< Math.max(rerollChance - emotes*2, 0)) {
            return pickMessage(messages);
        }

        lastSeen.put(message, current);
        current++;
        return message;
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
