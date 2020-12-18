package ninja.diku.conversation.conversations;

import ninja.diku.command.Command;
import ninja.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class VerificationConversation extends GuildConversation {

    private String password;
    private String email;
    /**
     * VerificationConversation is used to keep track of the conversation triggered by !join
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  password A string, the password given to the user
     * @param email     A string, the email specified by the user
     * @return      void
     * @see         ninja.diku.command.commands.introduction.JoinCommand
     * @see         ninja.diku.command.commands.introduction.VerifyCommand
     * @see         ninja.diku.conversation.GuildConversation
     */
    public VerificationConversation(User user, Guild guild, String password, String email) {
        super(user, guild);
        this.password=password;
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return this.email;
    }
}
