package ninja.diku.ticket;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import ninja.diku.models.TicketModel;
import ninja.diku.models.exceptions.TicketNotFoundException;
import ninja.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.entities.*;

import java.util.UUID;

public abstract class Ticket {


    public enum Type {

        MAJOR(MajorTicket.class), GENERIC(Ticket.class);

        final Class<? extends Ticket> ticket;
        Type(Class<? extends Ticket> ticket) {
            this.ticket=ticket;
        }

        public Class<? extends Ticket> getTicket() {
            return ticket;
        }
    }


    protected UUID UUID;
    protected User user;
    protected Guild guild;
    /*public Ticket(User user) {
        this(user, java.util.UUID.randomUUID());
    }*/
    public Ticket(Guild guild, User user, UUID UUID) {
        this.UUID = UUID;
        this.user=user;
        this.guild=guild;
    }

    public static Ticket getTicket(UUID uuid) throws TicketNotFoundException {
        return TicketFactory.getTicket(TicketModel.getTicketModel(uuid));
    }

    public Guild getGuild() {
        return guild;
    }

    public UUID getUUID() {
        return this.UUID;
    }
    public User getUser() {
        return user;
    }
    abstract public Type getType();
    abstract public TicketDisplay getDisplay();
    abstract public TicketDisplay getCloseDisplay();
    abstract public String getContext();
    abstract public TextChannel getOpenTicketChannel();
    abstract public TextChannel getClosedTicketChannel();

    public void close(String comment) {
        TicketModel ticketModel = new TicketModel(this);
        String messageID = ticketModel.getMessageId();
        ticketModel.setOpen(false);

        TextChannel channel = getOpenTicketChannel();
        channel.retrieveMessageById(messageID).queue((message -> {
            message.delete().queue();
        }));

        MessageCreateBuilder messageBuilder = new MessageCreateBuilder();
        messageBuilder.addContent("```");
        messageBuilder.addContent(comment).addContent("\n");
        messageBuilder.addContent(getCloseDisplay().getMessage().getContent()).addContent("\n");
        messageBuilder.addContent("```");
        getClosedTicketChannel().sendMessage(messageBuilder.build()).queue();
    }

    public void activate() {
        TicketDisplay display = getDisplay();
        TicketModel ticketModel = new TicketModel(this);
        MessageCreateBuilder messageBuilder = new MessageCreateBuilder();
        messageBuilder.addContent(display.getMessage().getContent());
        MessageCreateData message = messageBuilder.build();

        getOpenTicketChannel().sendMessage(message).queue((sentMessage) ->
        {
            for(String emote : display.getActions().keySet()) {
                sentMessage.addReaction(Emoji.fromUnicode(emote)).queue();
            }
            ticketModel.setMessage(sentMessage);
        });

    }


}