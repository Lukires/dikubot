package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class SkipCommand implements DJCommand {
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
