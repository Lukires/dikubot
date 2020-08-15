package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Major;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

import java.util.List;

public class MajorCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);


        if(args.length < 2) {
            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()+" Invalid usage - !major [fag]\"\n");

            String subjects = "Fag:\n";
            for(Major major : Major.values()) {
                subjects+=major.getName()+"\n";
            }
            mb.appendCodeLine(subjects);
            channel.sendMessage(mb.build()).queue();
            return;
        }

        UserModel userModel = UserModel.getUserModel(user);
        if (!userModel.isVerified()) {
            channel.sendMessage(user.getAsMention()+" din email er ikke verified. Brug !verify [kode] for at blive verified").queue();
            return;
        }

        String majorInput = args[1];
        for(Major major : Major.values()) {
            if(major.getName().equalsIgnoreCase(majorInput)) {
                channel.sendMessage(user.getAsMention()+" dit fag er blevet sat til "+major.getName()).queue();

                //I know this is stupid but I can't be bothered to do it right
                for(Major majorin : Major.values()) {
                    guild.removeRoleFromMember(guild.getMember(user), guild.getRolesByName(majorin.getRole().getRole(), true).get(0)).queue();
                }

                userModel.setMajor(major.getName());
                guild.addRoleToMember(guild.getMember(user), guild.getRolesByName(major.getRole().getRole(), true).get(0)).queue();
                return;
            }
        }

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()+" Det indtastet fag findes ikke - !major [fag]\"\n");

        String subjects = "Fag:\n";
        for(Major major : Major.values()) {
            subjects+=major.getName()+"\n";
        }
        mb.appendCodeLine(subjects);
        channel.sendMessage(mb.build()).queue();
        return;



    }
}