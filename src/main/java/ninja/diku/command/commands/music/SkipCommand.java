package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class SkipCommand implements MusicCommand {

    /**
     * Skips the specified amount of songs
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
        String[] args = getArgs(message);
        int skips = 1;
        if (args.length > 1) {
            try {
                skips = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {
            }
        }
        String songs = skips == 1 ? "sang" : "sange";
        messageChannel.sendMessage(":arrow_right_hook: Skipping " + skips + " " +songs).queue();

        for(int i = 0; i < skips-1; i++) {
            player.getScheduler().pollQueue();
        }
        player.stopTrack();
    }

    @Override
    public String getDescription() {
        return "Spring musik over";
    }

    @Override
    public String getUsage() {
        return "!skip";
    }
}
