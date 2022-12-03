package ml.bigtruck2.ml;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class pingPong extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getMessage().getAuthor().getIdLong()!=851472844554240060L) {
            try {
                String msg = e.getMessage().getContentRaw();

                System.out.println(e.getMessage().getContentRaw() + " "+ e.getMessage().getAuthor());
                URL url = new URL("http://localhost:8081/v2/check?language=en-US&text=" + e.getMessage().getContentRaw().replace(" ", "%20").replace("\n","%20"));
                JsonArray array = new JsonParser().parse(new InputStreamReader(url.openStream())).getAsJsonObject().get("matches").getAsJsonArray();
                JsonArray newJsonArray = new JsonArray();
                for (int i = array.size()-1; i>=0; i--) {
                    newJsonArray.add(array.get(i));
                }
                int replacements = 0;
                for (JsonElement element : newJsonArray) {
                    int offset = element.getAsJsonObject().get("offset").getAsInt();
                    int length = element.getAsJsonObject().get("length").getAsInt();
                    System.out.println(offset+" "+length+msg.substring(offset,offset+length));
                    msg = msg.substring(0,offset)+"**"+element.getAsJsonObject().get("replacements").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString()+"**"+msg.substring(offset+length);
                    replacements++;
                }
                if(replacements>=1) {
                    e.getMessage().reply(msg).queue();
                }
            } catch (IOException | NullPointerException e2) {
                e2.printStackTrace();
            }
        }
            }
}