package ninja.diku.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public interface Command {

    /**
     * Used for command related events
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */
    void onCommand(User user, Guild guild, MessageChannel channel, Message message);

    String getDescription();

    String getUsage();

    /**
     * Splits the message into all of it's substrings seperated by space
     *
     * @param  message  The message object, the user wrote.
     * @return      String[]
     * @see         Command
     */
    default String[] getArgs(Message message) {
        return message.getContentRaw().split(" ");
    }

    /**
     * Removes the !command part of the string
     *
     * @param  message  The message object, the user wrote.
     * @return      String
     * @see         Command
     */
    default String removeCommand(Message message) {
        return message.getContentRaw().replaceFirst(message.getContentRaw().split(" ")[0], "");
    }
}
