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
        while(!isPrime(prime+2)) {
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
        if (n <= 1) return false;
        if (n <= 3) return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
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
