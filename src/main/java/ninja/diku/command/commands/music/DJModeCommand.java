package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.music.audio.AudioPlayer;

public class DJModeCommand implements DJCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message) {
        if (!DJCommand.isDJ(member, player)) {
            messageChannel.sendMessage(":x: Kun DJs kan slå DJ Mode til og fra").queue();
            return;
        }
        boolean mode = player.getContext().isDjMode();
        player.getContext().setDjMode(!mode);
        messageChannel.sendMessage(":white_check_mark: Du har nu sat DJ Mode til " + !mode).queue();
    }

    @Override
    public String getDescription() {
        return "Slå DJ Mode til og fra";
    }

    @Override
    public String getUsage() {
        return "!djmode";
    }
}
