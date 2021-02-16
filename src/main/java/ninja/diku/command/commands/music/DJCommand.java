package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioPlayer;

public interface DJCommand extends MusicCommand {

    static boolean isDJ(Member member, AudioPlayer player) {
        User user = member.getUser();
        Guild guild = player.getContext().getGuild();
        return Util.isRole(user, guild, "DJ") || Util.isMod(user, guild) || Util.isRole(user, guild, "RKG");
    }

}
