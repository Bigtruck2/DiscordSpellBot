package ml.bigtruck2.ml;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main {



    public static void main(String[] args){
        JDABuilder jdaBuilder = JDABuilder.createDefault("ODUxNDcyODQ0NTU0MjQwMDYw.GiiSnT.7WylJTCCcVD4bT24XCdRKGg_1YjuLU8_0c6m7o");
        pingPong ping = new pingPong();
        jdaBuilder.addEventListeners(ping);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        jdaBuilder.build();
    }
}
