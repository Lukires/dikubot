package ninja.diku.command.commands.fun;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Stack;

public class RPNCommand implements Command {

    /**
     * This command can be used to do calculations via reverse polish notation.
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
        Double result = calculate(removeCommand(message));
        if(result == null) {
            channel.sendMessage("Den ligning kan jeg ikke finde ud af :(").queue();
            return;
        }
        channel.sendMessage(result.toString()).queue();
    }

    @Override
    public String getDescription() {
        return "Reverse Polish notation calculator";
    }

    @Override
    public String getUsage() {
        return "!rpn [Ligning]";
    }

    /**
     * Calculates a reverse polish notation string
     *
     * @param  calculation The string to be calculated
     * @return double
     */
    public static double calculate(String calculation){
        return calculate(calculation, null);
    }

    public static double calculate(String calculation, HashMap<String, Double> vars){
        Stack<Double> stack = new Stack<Double>();

        String[] operations = calculation.trim().split(" ");

        if(vars == null)
            vars = new HashMap<String, Double>(0);

        for(String operation: operations) {
            if(isDouble(operation))
                stack.push(Double.parseDouble(operation));
            else if(vars.containsKey(operation))
                stack.push(vars.get(operation));
            else
                stack = calc(operation.toCharArray()[0], stack);
        }

        return stack.pop();
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static Stack<Double> calc(char op, Stack<Double> stack){
        switch(op){
            case '+':
                stack.push(stack.pop()+ stack.pop());
                break;
            case '-':
                stack.push(-stack.pop() + stack.pop());
                break;
            case '/':
                stack.push((1/stack.pop() * stack.pop()));
                break;
            case '*':
                stack.push((stack.pop() * stack.pop()));
                break;
            case '^':
                double temp = stack.pop();
                stack.push(Math.pow(stack.pop(), temp));
                break;
            case '_':
                //I know it's a silly square root symbol
                stack.push(Math.sqrt(stack.pop()));
                break;
            case 'c':
            case 'C':
            case 's':
            case 'S':
                stack.push(Math.cos(stack.pop()));
                break;
            default:
            case '\"': //These are "comments"
            case '\\':
                break;
        }
        return stack;
    }

}
