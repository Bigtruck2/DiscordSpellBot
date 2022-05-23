package ml.bigtruck2.ml;

import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {



    public static void main(String[] args){
        JDABuilder jdaBuilder = JDABuilder.createDefault("");
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
