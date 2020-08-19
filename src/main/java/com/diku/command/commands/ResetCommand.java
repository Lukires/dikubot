package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Roles;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

public class ResetCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {

        for (Roles role : Roles.values()) {
            try{
                guild.removeRoleFromMember(user.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
            }catch(ErrorResponseException e) {
                continue;
            }
        }
        UserModel.getUserModel(user).deleteUser();
        channel.sendMessage(user.getAsMention()+" din profil er blevet resettet").queue();
    }

    public String getDescription() {
        return "Reset din Discord profil";
    }

    @Override
    public String getUsage() {
        return "!reset";
    }
}
