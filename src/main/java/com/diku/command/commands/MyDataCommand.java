package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.conversation.GuildConversation;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class MyDataCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        user.openPrivateChannel().queue(privateChannel ->
        {
            UserModel model = new UserModel(user);
            Document document = model.getDocument();

            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()).append(" her er dataen du har anmodet om:\n");
            mb.append("```").append(document.toJson()).append("```");
            channel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(user, privateChannel, guild));
        });
    }

    @Override
    public String getDescription() {
        return "Anmod om alt dataen DIKU botten har på dig";
    }

    @Override
    public String getUsage() {
        return "!mydata";
    }
}
