package com.diku.main;

import com.diku.command.Command;
import com.diku.command.commands.*;
import com.diku.command.CommandListener;
import com.diku.greeting.JoinListener;
import com.diku.ticket.TicketListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    public static JDA jda;
    private static JDABuilder jdaBuilder;
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    /**
     * This is the main method. When adding new commands the command string and command object needs to be added to the
     * hash map here. The main method is also were eventListener is added, the bot activity is set and the bot is
     * initialized.
     *
     * @param  String[]     Arguments on boot
     * @return      void
     */

    public static void main(String[] args) {

        commands.put("!role", new RoleCommand());
        commands.put("!verify", new VerifyCommand());
        commands.put("!major", new MajorCommand());
        commands.put("!reset", new ResetCommand());
        commands.put("!amiaprodigy", new ProdigyCommand());
        commands.put("!ping", new PingCommand());
        commands.put("!help", new HelpCommand());
        commands.put("!lovecalc", new LoveCalcCommand());
        commands.put("!magic8ball", new Magic8BallCommand());
        commands.put("!rpn", new RPNCommand());
        commands.put("!roll", new RollCommand());
        commands.put("!group", new GroupCommand());
        commands.put("!citat", new QuoteCommand());
        commands.put("!quote", new QuoteCommand());
        commands.put("!prime", new PrimeCommand());
        commands.put("!clear", new ClearCommand());

        jdaBuilder = JDABuilder.createDefault(getAPIKey());
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.setActivity(Activity.watching("you"));
        jdaBuilder.addEventListeners(new CommandListener());
        jdaBuilder.addEventListeners(new JoinListener());
        jdaBuilder.addEventListeners(new TicketListener());
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return the API key. So when using the bot give insert an API key in
     * src/main/resources/apitoken.txt".
     *
     * @return      string      API Key
     */

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
