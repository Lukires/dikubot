package ninja.diku.command.commands.music;

import ninja.diku.command.Command;
import ninja.diku.main.Constant;
import ninja.diku.models.UserModel;
import net.dv8tion.jda.api.entities.*;
import ninja.diku.music.audio.*;

public class PlayCommand implements MusicCommand {

    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
        onCommand(member, guild, messageChannel, voiceChannel, player, message, false);
    }

    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message, boolean playTop) {
        String[] args = getArgs(message);

        if(args.length < 2) {
            messageChannel.sendMessage("Forkert bruge! " + getUsage()).queue();
            return;
        }

        StringBuilder search = new StringBuilder();
        for (int i = 1; i<args.length; i++) {
            search.append(args[i]);
        }

        if (!Constant.YOUTUBE_URL_REGEX.matcher(search.toString()).matches()) {
            search.insert(0, "ytsearch:");
        }

        audioManager.loadItemOrdered(guild, search.toString(), new LoadAudioHandler(player, playTop));
    }

    @Override
    public String getDescription() {
        return "Spil musik!";
    }

    @Override
    public String getUsage() {
        return "!play <yt link / yt search query>";
    }
}
