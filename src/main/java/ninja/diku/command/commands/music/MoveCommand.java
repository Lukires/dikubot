package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.command.commands.introduction.VerifyCommand;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class MoveCommand implements MusicCommand {
    /**
     * Move the music bot to the specified voiceChannel and messageChannel
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
        if(player.getPlayingTrack() == null) {
            messageChannel.sendMessage(":x: Du kan kun flytte mig når jeg spiller musik").queue();
            return;
        }

        MessageChannel currentMessageChannel = player.getContext().getMessageChannel();
        VoiceChannel currentVoiceChannel = player.getContext().getVoiceChannel();
        player.getContext().setMessageChannel(messageChannel);
        player.getContext().setVoiceChannel(voiceChannel);

        if (!player.getScheduler().joinVoiceChannel()) {
            messageChannel.sendMessage(":x: Fejl, jeg har ikke adgang til at spille i din kanal. Jeg bliver hvor jeg er!").queue();
            player.getContext().setMessageChannel(currentMessageChannel);
            player.getContext().setVoiceChannel(currentVoiceChannel);
            return;
        }
        messageChannel.sendMessage(":airplane: Du har flyttet mig til **voice channel: " + voiceChannel.getName() + "** og **message channel: " + messageChannel.getName()+ "**").queue();
    }

    @Override
    public String getDescription() {
        return "Flyt musik botten til den kanal du er i";
    }

    @Override
    public String getUsage() {
        return "!move";
    }
}
