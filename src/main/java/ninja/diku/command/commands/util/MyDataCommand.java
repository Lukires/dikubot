package ninja.diku.command.commands.util;

import ninja.diku.command.Command;
import ninja.diku.conversation.GuildConversation;
import ninja.diku.database.Collections;
import ninja.diku.models.UserModel;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class MyDataCommand implements Command {
    /**
     * Will print all data we currently held on the user to the user
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
            mb.append("}```");
            privateChannel.sendMessage(mb.build()).queue();
            GuildConversation.addConversation(new GuildConversation(user, guild));
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
