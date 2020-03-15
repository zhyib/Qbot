package listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.IcqHttpApi;
import word.Reply;

import java.io.FileNotFoundException;
import java.util.Random;

public class ListenerChat extends IcqListener {
    @EventHandler
    public void autoReply(EventGroupMessage event) {
        String[][][] wordsReply = Reply.pair;
        Random random = new Random();

        IcqHttpApi icqHttpApi = event.getHttpApi();
        for (String[][] strings : wordsReply) {
            for (int j = 0; j < strings[0].length; j++) {
                if (event.getMessage().contains(strings[0][j])) {
                    icqHttpApi.sendGroupMsg(event.getGroupId(), strings[1][random.nextInt(strings[1].length)]);
                }
            }
        }
    }

    @EventHandler
    public void copyReply(EventGroupMessage event) {
        String[] wordsCopy = Reply.copyKeyword;
        IcqHttpApi icqHttpApi = event.getHttpApi();
        for (String s : wordsCopy) {
            if (event.getMessage().contains(s)) {
                icqHttpApi.sendGroupMsg(event.getGroupId(), event.getMessage());
            }
        }
    }
}
