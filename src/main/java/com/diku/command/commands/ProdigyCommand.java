package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class ProdigyCommand implements Command {

    /**
     * This command retrieves the prodigy percentage from the database. This percentage will tell your chances of being
     * a prodigy.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */

    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        double percentile = UserModel.getUserModel(user).getProdigyPercentile();
        if(percentile > 98) {
            channel.sendMessage(user.getAsMention() + " you're in the " + percentile + "th percentile, consider yourself a prodigy").queue();
        } else if(percentile > 90) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, you're close but not quite a prodigy").queue();
        }else if(percentile > 80) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, far above average - and far from a prodigy").queue();
        }else if(percentile > 70) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, little above average, where you belong").queue();
        }else if(percentile < 68 && percentile > 70) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile. Nice").queue();
        } else if(percentile > 40) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, congratulations! You're very average").queue();
        }else if(percentile > 20) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, you're like the opposite of a prodigy").queue();
        }else if(percentile > 10) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, you doing okay there bud?").queue();
        }else if(percentile > 5) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile, how did you even make it this far").queue();
        }else if(percentile > 1) {
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile... I'm sorry...").queue();
        }else{
            channel.sendMessage(user.getAsMention()+" you're in the "+percentile+"th percentile").queue();
        }
    }

    public String getDescription() {
        return "-3 eller 12?";
    }

    @Override
    public String getUsage() {
        return "!amiaprodigy";
    }

}
