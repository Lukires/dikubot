package com.diku.main;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.nio.charset.Charset;
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

    public static boolean isMod(User user, Guild guild) {
        List<Role> roles = user.getJDA().getRoles();
        Role modRole = guild.getRolesByName("mod", true).get(0);
        return roles.contains(modRole);
    }
}
