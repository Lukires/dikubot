package com.diku.command.commands;

import com.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class PrimeCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        long twinPrime = getRandomTwinPrime();
        channel.sendMessage("Her er et primtal "+getRandomTwinPrime()+ " og her er dens tvilling "+twinPrime+2).queue();
    }

    private long getRandomTwinPrime() {
        long prime = getRandomPrime();
        if(isPrime(prime+2)) {
            return prime;
        }
        while(!isPrime(+2)) {
            prime+=6;
        }
        return prime;
    }

    private long getRandomPrime() {
        Random random = new Random();
        long i = random.nextLong();
        if (!isPrime(i)) {
            i = getRandomPrime();
        }
        return i;
    }

    private boolean isPrime(long n) {
        if (n <= 3) {
            return n > 1;
        }

        if(n%2 == 0 || n%3 == 0) {
            return false;
        }
        return optimizePrime(n, 5);
    }

    private boolean optimizePrime(long n, long i) {
        if (i * i > n) {
            return true;
        }

        if(n%i == 0 || n%(i+2) == 0) {
            return false;
        }

        return optimizePrime(n, (i+6));

    }



    @Override
    public String getDescription() {
        return "Returns a prime - and its twin!";
    }

    @Override
    public String getUsage() {
        return "!prime";
    }
}
