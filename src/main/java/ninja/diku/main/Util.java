package ninja.diku.main;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class Util {
    public static String generateString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }


    public static boolean isRole(User user, Guild guild, String role) {
        Role modRole = guild.getRolesByName(role, true).get(0);
        Member member = guild.retrieveMember(user).complete();
        try {
            List<Role> Roles = member.getRoles();
            return Roles.contains(modRole);
        } catch (NullPointerException e) { // A user may have no roles so this exception makes sure false get returned.
            return false;
        }
    }

    /**
     * This function checks if a user is a mod. True if they are mod, False if they are not.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @return      bool
     */
    public static boolean isMod(User user, Guild guild) {
        return isRole(user, guild, "mod");
    }

    /**
     * This function checks if a user is a mod. True if they are mod, False if they are not.
     *
     * @param  member   The member object.
     * @param  guild    The guild object, the user belongs to.
     * @return      bool
     */
    public static boolean isMod(Member member, Guild guild) {
        return isMod(member.getUser(), guild);
    }

    public static String milisecondsToMinuteString(long time) {
        return new DecimalFormat("0.00").format(((float)time) / 1000.0f / 60.0f);
    }

}
