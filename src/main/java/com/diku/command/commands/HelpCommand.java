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
        String[] args = getArgs(message);
        if(args.length>1) {
            String command = args[1];
            command = command.startsWith("!")?command:"!"+command;
            if (!Main.commands.containsKey(command)) {
                channel.sendMessage(user.getAsMention()+" kunne ikke finde kommandoen "+args[1]).queue();
                return;
            }
            channel.sendMessage(user.getAsMention()+" usage: "+Main.commands.get(command).getUsage()).queue();
            return;
        }

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()).append(" brug for hjælp? Her er en liste af alle mine kommandoer! \n");
        StringBuilder commands = new StringBuilder();
        for(String command : Main.commands.keySet()) {
            commands.append(command).append(" - ").append(Main.commands.get(command).getDescription()).append("\n");
        }
        mb.append("```").append(String.valueOf(commands)).append("```");
        channel.sendMessage(mb.build()).queue();
    }

    public String getDescription() {
        return "Viser dig alle kommandoer";
    }

    @Override
    public String getUsage() {
        return "!help (Command)";
    }


}
