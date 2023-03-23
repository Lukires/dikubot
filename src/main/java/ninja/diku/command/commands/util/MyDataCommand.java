package ninja.diku.command.commands.util;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.command.Command;
import ninja.diku.conversation.GuildConversation;
import ninja.diku.database.Collections;
import ninja.diku.models.UserModel;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class MyDataCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannelUnion channel, Message message) {
        user.openPrivateChannel().queue(privateChannel ->
        {
            UserModel userModel = new UserModel(user);
            Document userDocument = userModel.getDocument();

            MessageCreateBuilder mb = new MessageCreateBuilder();
            mb.addContent(user.getAsMention()).addContent(" her er dataen du har anmodet om:\n");
            mb.addContent("```json\n{user: ").addContent(userDocument.toJson()).addContent(",");
            mb.addContent("tickets: ");
            for (Document ticket : Collections.TICKETS.getCollection().find(Filters.eq("user", user.getId()))) {
                mb.addContent(ticket.toJson()).addContent(",");
            }
            mb.addContent("}```");
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
