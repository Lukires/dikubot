package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Roles;
import com.diku.main.Main;
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

        List<Role> roles = Objects.requireNonNull(guild.getMemberById(user.getId())).getRoles();
        Role modRole = guild.getRolesByName("mod", true).get(0);
        if(roles.contains(modRole)) {
            try {
                User tagged = Main.jda.getUserByTag(args[1]);
                for (Roles role : Roles.values()) {
                    try{
                        assert tagged != null;
                        guild.removeRoleFromMember(tagged.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
                    }catch(ErrorResponseException ignored) {
                    }
                }
                UserModel.getUserModel(tagged).delete();
                assert tagged != null;
                channel.sendMessage(tagged.getName()+" er blevet resettet").queue();
            } catch (Exception ignored) {
                channel.sendMessage(user.getAsMention()+" kunne ikke finde "+args[1]).queue();
            }
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
