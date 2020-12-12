package ninja.diku.command.commands.fun;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class PrimeCommand implements Command {

    /**
     * This command will return a prime.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */

    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        long prime = getRandomPrime();
        channel.sendMessage("Her er et primtal! "+prime).queue();
    }

    private long getRandomPrime() {
        long i = 0;
        Random random = new Random();
        while(!isPrime(i)) {
            i = random.nextLong();
            if(i < 0) {
                i*=-1;
            }
            if(i % 2 == 0) {
                i+=1;
            }
            i = (long) Math.sqrt((double)(i));
        }
        return i;
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        int count = 0;
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            if(count > 10000) {
                return false;
            }
            count++;
        }
        return true;
    }




    @Override
    public String getDescription() {
        return "Primtal til dig!";
    }

    @Override
    public String getUsage() {
        return "!prime";
    }
}
