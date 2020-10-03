package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.main.Util;
import net.dv8tion.jda.api.entities.*;

import java.util.ArrayList;

public class ClearCommand implements Command {

    // Should be using a database
    private static ArrayList<Long> clearable = new ArrayList<>();

    /**
     * A command which can clear a chat if typed by one with mod rank. "!clear add" will make the chat clearable,
     * "!clear remove" will make the chat unclearable.
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
        String[] args = getArgs(message);
        if (Util.isMod(user, guild)) {
            if (args.length == 1) {
                if (!clearable.contains(channel.getIdLong())) {
                    channel.sendMessage("I denne channel kan !clear ikke bruges.").queue();
                } else {
                    MessageHistory.getHistoryFromBeginning(channel).queue(messageHistory -> {
                        channel.purgeMessages(messageHistory.getRetrievedHistory());
                    });
                }
            } else if (args.length == 2) {
                if (args[1].equals("add")) {
                    if (!clearable.contains(channel.getIdLong())) {
                        clearable.add(channel.getIdLong());
                    }
                    channel.sendMessage("!clear kan nu bruges i denne channel.").queue();
                } else if (args[1].equals("remove")) {
                    clearable.remove(channel.getIdLong());
                    channel.sendMessage("!clear kan ikke længere bruges i denne channel.").queue();
                } else {
                    channel.sendMessage("Et invalid input var givet.").queue();
                }
            } else {
                channel.sendMessage("Et invalid input var givet.").queue();
            }
        } else {
            channel.sendMessage("Du har ikke rettigheder til af bruge !clear.").queue();
        }

        return;
    }

    @Override
    public String getDescription() {
        return "Fjerner beskeder fra chatten.";
    }

    @Override
    public String getUsage() {
        return "!clear, !clear add eller !clear remove";
    }
}
