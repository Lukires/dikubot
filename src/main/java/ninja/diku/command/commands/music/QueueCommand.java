package ninja.diku.command.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.*;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QueueCommand implements MusicCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, Message message) {
        AudioPlayer player = audioManager.getPlayer(new AudioContext(guild, voiceChannel, messageChannel));

        ArrayList<AudioTrack> queue = player.getScheduler().getQueue();
        if (queue.isEmpty()) {
            messageChannel.sendMessage("Køen er tom! :(").queue();
            return;
        }
        StringBuilder queueString = new StringBuilder("```");

        for (AudioTrack track : queue) {
            String time = Util.milisecondsToMinuteString(track.getDuration());
            queueString.append(track.getInfo().title).append(" - Længde: ").append(time).append(" min. \n\n");
        }
        queueString.append("```");
        messageChannel.sendMessage(queueString).queue();
    }

    @Override
    public String getDescription() {
        return "Viser en liste af musik i køen";
    }

    @Override
    public String getUsage() {
        return "!queue";
    }
}
