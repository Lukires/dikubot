package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class KillMusicCommand implements DJCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message) {
        audioManager.purgePlayer(guild);
        messageChannel.sendMessage(member.getAsMention() + " you killed my jam :(").queue();
    }

    @Override
    public String getDescription() {
        return "Dræb musikken - Kun mods";
    }

    @Override
    public String getUsage() {
        return "!killmusic";
    }
}
