package com.diku.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public interface Command {

    void onCommand(User user, Guild guild, MessageChannel channel, Message message);

    String getDescription();

    //Removes the !command from the start of the message

    default String[] getArgs(Message message) {
        return message.getContentRaw().split(" ");
    }

    default String removeCommand(Message message) {
        return message.getContentRaw().replaceFirst(message.getContentRaw().split(" ")[0], "");
    }
}
