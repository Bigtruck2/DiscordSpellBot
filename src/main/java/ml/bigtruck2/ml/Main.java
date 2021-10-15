package ml.bigtruck2.ml;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDABuilder jdaBuilder = JDABuilder.createDefault(scanner.next());
        pingPong ping = new pingPong();
        jdaBuilder.addEventListeners(ping);
        try {
            jdaBuilder.build();
        }
        catch (LoginException e){
            e.printStackTrace();
        }

    }
}
