package ninja.diku.main;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ninja.diku.command.Command;
import ninja.diku.command.CommandListener;
import ninja.diku.command.commands.fun.*;
import ninja.diku.command.commands.introduction.JoinCommand;
import ninja.diku.command.commands.introduction.MajorCommand;
import ninja.diku.command.commands.introduction.VerifyCommand;
import ninja.diku.command.commands.music.*;
import ninja.diku.command.commands.util.*;
import ninja.diku.database.Collections;
import ninja.diku.greeting.JoinListener;
import ninja.diku.ticket.TicketListener;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.hooks.InterfacedEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bson.Document;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static JDA jda;
    private static JDABuilder jdaBuilder;
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    /**
     * This is the main method. When adding a new commands, the command object and command string needs to be added to
     * the hash map here. The main method is also were eventListeners is added, the bot activity is set and the bot is
     * initialized.
     *
     * @param  args     Arguments on boot
     * @return      void
     */

    public static void main(String[] args) {

        //commands.put("!join", new JoinCommand());
        //commands.put("!verify", new VerifyCommand());
        //commands.put("!major", new MajorCommand());
        //commands.put("!reset", new ResetCommand());
        //commands.put("!amiaprodigy", new ProdigyCommand());
        //commands.put("!ping", new PingCommand());
        commands.put("?help", new HelpCommand());
        //commands.put("!lovecalc", new LoveCalcCommand());
        //commands.put("!magic8ball", new Magic8BallCommand());
        //commands.put("!rpn", new RPNCommand());
        //commands.put("!roll", new RollCommand());
        //commands.put("!group", new GroupCommand());
        //commands.put("!citat", new QuoteCommand());
        //commands.put("!quote", new QuoteCommand());
        //commands.put("!prime", new PrimeCommand());
        //commands.put("!clear", new ClearCommand());
        //commands.put("!mydata", new MyDataCommand());
        commands.put("?play", new PlayCommand());
        commands.put("?pl", new PlayCommand());
        commands.put("?playtop", new PlayTopCommand());
        commands.put("?pltop", new PlayTopCommand());
        commands.put("?move", new MoveCommand());
        commands.put("?skip", new SkipCommand());
        commands.put("?queue", new QueueCommand());
        commands.put("?killmusic", new KillMusicCommand());
        commands.put("?playing", new PlayingCommand());
        commands.put("?djmode", new DJModeCommand());

        jdaBuilder = JDABuilder.createDefault(getAPIKey());
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.setActivity(Activity.playing("Use ? to play"));
        jdaBuilder.addEventListeners(new CommandListener());
        //jdaBuilder.addEventListeners(new JoinListener());
        //jdaBuilder.addEventListeners(new TicketListener());
        jdaBuilder.setEventManager(new ThreadedEventManager());
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
        jda = jdaBuilder.build();
    }

    private static class ThreadedEventManager extends InterfacedEventManager {
        private final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

        public void handle(Event e) {
            threadPool.submit(() -> super.handle(e));
        }
    }

    private static void hashAllEmails() {
        //collection.updateOne(Filters.eq("_id", getID()), new Document("$set", new Document(key, value)));
        MongoCollection<Document> userCollection = Collections.USERS.getCollection();
        for (Document user : userCollection.find()) {
            if (Constant.VALID_EMAIL_REGEX.matcher(user.getString("email")).matches()) {
                String hashedEmail = BCrypt.withDefaults().hashToString(Constant.HASH_COST, user.getString("email").toCharArray());
                userCollection.updateOne(Filters.eq("_id", user.getString("_id")), new Document("$set", new Document("email", hashedEmail)));
            }
        }
    }

    /**
     * This method will return the API key. So when using the bot insert an API key in src/main/resources/apitoken.txt".
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
