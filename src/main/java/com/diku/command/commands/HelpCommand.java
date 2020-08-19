package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Major;
import com.diku.main.Main;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class HelpCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()+" brug for hjælp? Her er en liste af alle mine kommandoer! \n");
        String commands = "";
        for(String command : Main.commands.keySet()) {
            commands+=command+"\n";
        }
        mb.appendCodeLine(commands);
        channel.sendMessage(mb.build()).queue();
    }
}
