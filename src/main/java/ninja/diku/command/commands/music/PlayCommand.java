package ninja.diku.command.commands.music;

import ninja.diku.command.Command;
import ninja.diku.main.Constant;
import ninja.diku.models.UserModel;
import net.dv8tion.jda.api.entities.*;
import ninja.diku.music.audio.*;

public class PlayCommand implements MusicCommand {

    /**
     * Play music in the given voiceChannel and send related messages in messageChannel
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

        audioManager.loadItemOrdered(guild, search.toString(), new LoadAudioHandler(player));

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
