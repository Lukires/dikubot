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
            for (Roles role : Roles.values()) {
                try{
                    guild.removeRoleFromMember(user.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
                }catch(ErrorResponseException e) {
                    continue;
                }
            }
            UserModel.getUserModel(user).delete();
            channel.sendMessage(user.getAsMention()+" din profil er blevet resettet").queue();
            return;
        }


        if(Util.isMod(user, guild)) {
            try {
                try{
                    Long id = Long.parseLong(args[1]);
                    UserModel.resetUserByID(args[1]);
                }catch (Exception e) {
                    User tagged = message.getMentionedUsers().size()==0?Main.jda.getUserByTag(args[1]):message.getMentionedUsers().get(0);
                    for (Roles role : Roles.values()) {
                        try{
                            assert tagged != null;
                            guild.removeRoleFromMember(tagged.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
                        }catch(ErrorResponseException ignored) {
                        }
                    }
                    UserModel.getUserModel(tagged).delete();
                    assert tagged != null;
                }
            } catch (Exception ignored) {
            }
            channel.sendMessage(args[1]+" er blevet resettet").queue();
        }

    }


    public String getDescription() {
        return "Reset din Discord profil";
    }

    @Override
    public String getUsage() {
        return "!reset";
    }
}
