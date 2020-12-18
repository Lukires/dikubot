package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.command.commands.introduction.VerifyCommand;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class KillMusicCommand implements MusicCommand {

    /**
     * Kill the music bot and reset it
     *
     * @param  member   The member object.
     * @param  guild    The guild object, the user belongs to.
     * @param  messageChannel  The channel object, the message was written in.
     * @param  voiceChannel  The voice channel object, which the user is in.
     * @param  player        The audio player object, which is used to control the music in the given Guild.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         MusicCommand
     * @see Command
     */
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
        if (!Util.isMod(member, guild)) {
            messageChannel.sendMessage(member.getAsMention() + " kun mods kan bruge denne kommando").queue();
            return;
        }
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
