package com.diku.main;

import com.diku.command.Command;
import com.diku.command.commands.MajorCommand;
import com.diku.command.commands.RoleCommand;
import com.diku.command.commands.VerifyCommand;
import com.diku.listeners.CommandListener;
import com.diku.listeners.JoinListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static JDA jda;
    private static JDABuilder jdaBuilder;
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public static void main(String[] args) {

        commands.put("!role", new RoleCommand());
        commands.put("!verify", new VerifyCommand());
        commands.put("!major", new MajorCommand());

        jdaBuilder = JDABuilder.createDefault(getAPIKey());
        jdaBuilder.setActivity(Activity.watching("you"));
        jdaBuilder.addEventListeners(new CommandListener());
        jdaBuilder.addEventListeners(new JoinListener());
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private static String getAPIKey() {
        File file = new File("src/main/resources/apitoken.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
