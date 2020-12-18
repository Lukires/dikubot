package ninja.diku.conversation.conversations;

import ninja.diku.conversation.GuildConversation;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class VerificationConversation extends GuildConversation {

    private String password;
    private String email;
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
