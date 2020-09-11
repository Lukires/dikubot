package com.diku.command.commands;

import com.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class RollCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);
        try {
            Random random = new Random();
            int number = Integer.parseInt(args[1]);
            int rolled = random.nextInt(number)+1;
            channel.sendMessage(user.getAsMention()+" du slog "+rolled).queue();
        }catch(NumberFormatException e) {
            channel.sendMessage("Jeg forstod ikke dit input. !roll [number]").queue();
        }
    }

    @Override
    public String getDescription() {
        return "Smid en terning mellem 1 og dit tal";
    }

    @Override
    public String getUsage() {
        return "!role [number]";
    }
}
