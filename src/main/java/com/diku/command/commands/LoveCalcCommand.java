package com.diku.command.commands;

import com.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoveCalcCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        // Takes input removes "!lovecalc ".
        String stringInput = message.getContentRaw().substring(10);
        if (!stringInput.contains(";")) {
            channel.sendMessage("Der mangler et semikolon til af separerer de to strenge.").queue();
            return;
        }

        // Checks if the command is being used correctly.
        String[] strings = stringInput.split(";");
        if (strings.length < 2) {
            channel.sendMessage("Der er blevet givet mindst et invalid streng.").queue();
            return;
        } else if (strings.length > 2) {
            channel.sendMessage("Der må kun være et semikolon til af separerer de to strenge.").queue();
            return;
        }

        // Regex to remove spaces/tabs in front or in the end of the message.
        String pattern = "([ \\t]+$)|(^[ \\t]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m;
        StringBuffer temp;
        for (int i = 0; i < strings.length; i++) {
            m = r.matcher(strings[i]);
            temp = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(temp, "");
            }
            m.appendTail(temp);
            strings[i] = temp.toString();
        }

        // Sums over numerical values from input.
        char[] input = stringInput.toCharArray();
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != ' ' || input[i] != ';') {
                sum += Character.getNumericValue(input[i]);
            }
        }

        // Generate a random percentage which is always the same.
        Random rand = new Random();
        rand.setSeed(sum);
        int p = rand.nextInt(101);
        channel.sendMessage("\"" + strings[0] + "\" og \"" + strings[1] + "\" har en kærligheds procent på " + p + "%").queue();
        return;
    }

    public String getDescription() {
        return "Udregner kærligheds procenten får to ting.";
    }

    @Override
    public String getUsage() {
        return "!lovecalc string0; string1";
    }
}
