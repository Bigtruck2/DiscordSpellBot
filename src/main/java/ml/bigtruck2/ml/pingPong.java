package ml.bigtruck2.ml;

import net.dv8tion.jda.api.entities.*;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.HashMap;

public class pingPong extends ListenerAdapter{
    private final HashMap<MessageChannel, String> message = new HashMap<>();
    private final HashMap<MessageChannel, Boolean> bol = new HashMap<>();
    private final HashMap<MessageChannel, String> lastMessage = new HashMap<>();
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getChannel().getId().equals("759123508428013591")){
            if(e.getAuthor().getId().equalsIgnoreCase("239631525350604801")){
                e.getMessage().delete().queue();
            }
        }
        if(lastMessage.get(e.getChannel()) == null){
            lastMessage.put(e.getChannel(),e.getMessage().getContentRaw());
        }else {
            if((lastMessage.get(e.getChannel()).toLowerCase().startsWith("p! play") ||lastMessage.get(e.getChannel()).toLowerCase().startsWith("p! skip")||lastMessage.get(e.getChannel()).toLowerCase().startsWith("p! stop"))&& e.getAuthor().getId().equalsIgnoreCase("239631525350604801")) {
                e.getMessage().delete().queue();
            }
            if(!e.getAuthor().getId().equalsIgnoreCase("239631525350604801")){
                lastMessage.replace(e.getChannel(),e.getMessage().getContentRaw());
            }
        }
        if(e.getMessage().getContentRaw().startsWith("ping")){
            if(e.getGuild().getId().equalsIgnoreCase("818641358768177182") &&!e.getChannel().getId().equalsIgnoreCase("898322800786415636")){
                    return;
            }
            int pos = e.getMessage().getContentRaw().indexOf(" ");
            int pos1 = e.getMessage().getContentRaw().indexOf(" ", pos+1);
            String user = "<@" + e.getMessage().getMentionedUsers().get(0).getId() + ">";
            user = user + e.getMessage().getContentRaw().substring(pos1);
            message.put(e.getChannel(), user);
            bol.put(e.getChannel(),true);
            e.getChannel().sendMessage(message.get(e.getChannel())).queue();
        }
        if(e.getMessage().getContentRaw().equalsIgnoreCase(message.get(e.getChannel())) && bol.get(e.getChannel()) && !e.getChannel().getId().equals("818641358768177184")){
            e.getChannel().sendMessage(message.get(e.getChannel())).queue();
        }
        if(e.getMessage().getContentRaw().toLowerCase().startsWith("stop")){
            bol.put(e.getChannel(),false);
            if(bol.get(e.getChannel()) != null){
                bol.replace(e.getChannel(),false);
            }else {
                bol.put(e.getChannel(),false);
            }
            if(message.get(e.getChannel()) != null){
                message.replace(e.getChannel(),"");
            }else {
                message.put(e.getChannel(),"");
            }
        }
        if(e.getMessage().getContentRaw().toLowerCase().startsWith("pause")){
            if(bol.get(e.getChannel()) != null){
                bol.replace(e.getChannel(),false);
            }else {
                bol.put(e.getChannel(),false);
            }
        }
        if(e.getMessage().getContentRaw().toLowerCase().startsWith("resume")){
            if(bol.get(e.getChannel()) != null){
                bol.replace(e.getChannel(),true);
            }else {
                bol.put(e.getChannel(),true);
            }
            e.getChannel().sendMessage(message.get(e.getChannel())).queue();
        }

    }
}