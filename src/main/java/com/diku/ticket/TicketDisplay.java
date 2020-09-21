package com.diku.ticket;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class TicketDisplay {
    public static class Builder {


        private User user;
        private Message message;

        public HashMap<Emote, TicketAction> actions = new HashMap<Emote, TicketAction>();
        public void addAction(Emote emote, TicketAction action) {
            actions.put(emote, action);
        }
        public void setUser(User user) {
            this.user=user;
        }

        public void setMessage(Message message) {
            this.message=message;
        }



        public TicketDisplay build() {
            return new TicketDisplay(this);
        }
    }


    protected TicketDisplay(Builder builder) {

    }
}
