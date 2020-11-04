package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.conversation.GuildConversation;
import com.diku.database.Collections;
import com.diku.models.UserModel;
import com.mongodb.client.model.Filters;
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
            UserModel userModel = new UserModel(user);
            Document userDocument = userModel.getDocument();

            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()).append(" her er dataen du har anmodet om:\n");
            mb.append("```{user: ").append(userDocument.toJson()).append(",");
            mb.append("tickets: ");
            for (Document ticket : Collections.TICKETS.getCollection().find(Filters.eq("user", user.getId()))) {
                mb.append(ticket.toJson()).append(",");
            }
            mb.append("```}");
            privateChannel.sendMessage(mb.build()).queue();
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
