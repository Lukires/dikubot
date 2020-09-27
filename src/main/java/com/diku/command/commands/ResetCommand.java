package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Roles;
import com.diku.main.Main;
import com.diku.main.Util;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

import java.util.List;
import java.util.Objects;

public class ResetCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);

        if (args.length<=1) {
            resetUser(user, guild);
            channel.sendMessage(user.getAsMention()+" din profil er blevet resettet").queue();
            return;
        }

        if(Util.isMod(user, guild)) {
            User tagged = message.getMentionedUsers().size()==0?Main.jda.getUserById(args[1]):message.getMentionedUsers().get(0);
            if (tagged == null) {
                Main.jda.retrieveUserById(args[1]).queue((target -> {
                    resetUser(target, guild);
                }), (failure) -> {
                    channel.sendMessage(user.getAsMention()+" kunne ikke finde "+args[1]).queue();
                });
            }else {
                resetUser(tagged, guild);
            }
            channel.sendMessage(args[0]+" er blevet resettet").queue();
        }
    }

    private void resetUser(User user, Guild guild) {
        for (Roles role : Roles.values()) {
            try{
                guild.removeRoleFromMember(user.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
            }catch(ErrorResponseException ignored) {
            }
        }
        UserModel.getUserModel(user).delete();
    }


    public String getDescription() {
        return "Reset din Discord profil";
    }

    @Override
    public String getUsage() {
        return "!reset";
    }
}
