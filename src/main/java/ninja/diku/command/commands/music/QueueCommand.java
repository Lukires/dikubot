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
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
        String[] args = getArgs(message);

        int page = 1;
        int pageSize = 10;
        if (args.length > 1) {
            try {
                page = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {

            }
        }

        ArrayList<AudioTrack> queue = player.getScheduler().getQueue();
        if (queue.isEmpty()) {
            messageChannel.sendMessage("Køen er tom! :(").queue();
            return;
        }
        StringBuilder queueString = new StringBuilder("Viser side: " + page + "/" + Math.ceil((double)queue.size() / (double)pageSize) + "\n```");

        for (int i = pageSize*(page-1); i < queue.size(); i++) {
            AudioTrack track = queue.get(i);
            String time = Util.milisecondsToMinuteString(track.getDuration());
            queueString.append(track.getInfo().title).append(" - Længde: ").append(time).append(" min. \n\n");
        }
        queueString.append("```\n Brug !queue [side tal] for at skifte side");
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
