package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class ResetCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        UserModel.getUserModel(user).deleteUser();
    }
}
