package ninja.diku.music.audio;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class AudioContext {
    private Guild guild;
    private VoiceChannel voiceChannel;
    private MessageChannel messageChannel;
    public AudioContext(Guild guild, VoiceChannel voiceChannel, MessageChannel messageChannel) {
        this.guild=guild;
        this.voiceChannel=voiceChannel;
        this.messageChannel=messageChannel;
    }

    public Guild getGuild() {
        return guild;
    }

    public VoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    public void setVoiceChannel(VoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public MessageChannel getMessageChannel() {
        return messageChannel;
    }

    public void setMessageChannel(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }
}
