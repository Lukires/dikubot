package com.diku.command.commands;

import com.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class PingCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        channel.sendMessage("Pong!").queue();
    }

    public String getDescription() {
        return "Pong!";
    }

    @Override
    public String getUsage() {
        return "!ping";
    }
}
