package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.entities.*;

public class ResetCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {

        for (Role role : guild.getRoles()) {
            if(role.getName().equalsIgnoreCase("mod")) {
                continue;
            }
            guild.removeRoleFromMember(user.getId(), role).queue();
        }
        UserModel.getUserModel(user).deleteUser();
        channel.sendMessage(user.getAsMention()+" din profil er blevet resettet").queue();
    }

    public String getDescription() {
        return "Reset din Discord profil";
    }
}
