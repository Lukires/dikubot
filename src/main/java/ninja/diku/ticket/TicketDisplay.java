package ninja.diku.ticket;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.HashMap;

public class TicketDisplay {
    public static class Builder {


        private User user;
        private MessageCreateData message;

        private HashMap<String, TicketAction> actions = new HashMap<String, TicketAction>();
        public void addAction(String emote, TicketAction action) {
            actions.put(emote, action);
        }

        public void setUser(User user) {
            this.user=user;
        }

        public void setMessage(MessageCreateData message) {
            this.message=message;
        }

        public User getUser() {
            return user;
        }

        public MessageCreateData getMessage() {
            return message;
        }

        public HashMap<String, TicketAction> getActions() {
            return actions;
        }

        public TicketDisplay build() {
            return new TicketDisplay(this);
        }
    }

    private User user;
    private MessageCreateData message;
    private HashMap<String, TicketAction> actions = new HashMap<String, TicketAction>();


    protected TicketDisplay(Builder builder) {
        this.user=builder.user;
        this.actions=builder.getActions();
        this.message=builder.getMessage();
    }

    public User getUser() {
        return user;
    }

    public MessageCreateData getMessage() {
        return message;
    }

    public HashMap<String, TicketAction> getActions() {
        return actions;
    }
}
