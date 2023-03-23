package ninja.diku.ticket.tickets;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.ku.Major;
import ninja.diku.ticket.Ticket;
import ninja.diku.ticket.TicketDisplay;
import ninja.diku.ticket.ticketactions.AcceptMajorTicketAction;
import ninja.diku.ticket.ticketactions.RejectMajorTicketAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class MajorTicket extends Ticket {


    private Major major;

    public MajorTicket(Guild guild, User user, java.util.UUID UUID, Major major) {
        super(guild, user, UUID);
        this.major=major;
    }

    @Override
    public Type getType() {
        return Type.MAJOR;
    }

    public Major getMajor() {
        return this.major;
    }

    public String getContext() {
        return major.name();
    }

    @Override
    public TextChannel getClosedTicketChannel() {
        return guild.getTextChannelById("756603513177374771");
    }

    @Override
    public TextChannel getOpenTicketChannel() {
        return guild.getTextChannelById("756470614323101736");
    }

    @Override
    public TicketDisplay getCloseDisplay() {
        TicketDisplay.Builder builder = new TicketDisplay.Builder();
        builder.setUser(getUser());

        MessageCreateBuilder mb = new MessageCreateBuilder();
        Member member = guild.retrieveMember(user).complete();
        String name = member.getNickname();
        if(name==null) {
            name = member.getEffectiveName();
        }
        mb.addContent(name).addContent(" (").addContent(user.getAsMention()).addContent(") har anmodet at deres !major bliver sat til ")
                .addContent(getMajor().getRole().getRole());
        builder.setMessage(mb.build());
        return builder.build();
    }

    @Override
    public TicketDisplay getDisplay() {
        TicketDisplay.Builder builder = new TicketDisplay.Builder();
        builder.addAction("U+2705", new AcceptMajorTicketAction());
        builder.addAction("U+274c", new RejectMajorTicketAction());
        builder.setUser(getUser());

        MessageCreateBuilder mb = new MessageCreateBuilder();
        Member member = guild.retrieveMember(user).complete();
        String name = member.getNickname();
        if(name==null) {
            name = member.getEffectiveName();
        }
        mb.addContent(name).addContent(" (").addContent(user.getAsMention()).addContent(") har anmodet at deres !major bliver sat til ")
                .addContent(getMajor().getRole().getRole());
        mb.addContent("\nAccepter: ").addContent(":white_check_mark:").addContent(" Afvis: ").addContent(":x:");
        builder.setMessage(mb.build());
        return builder.build();
    }
}
