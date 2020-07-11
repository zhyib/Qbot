package qbot.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.IcqHttpApi;
import qbot.Qbot;

public class ListenerRepeater extends IcqListener {
    @EventHandler
    public void listenerCommand(EventGroupMessage event) {
        IcqHttpApi icqHttpApi = event.getHttpApi();
        if (event.getMessage().charAt(0) != '#')
        if (event.getSender().getId() != 259232161L) {
            if (event.getMessage().equals(Qbot.lastWord)) {
                icqHttpApi.sendGroupMsg(event.getGroupId(), event.getMessage());
                Qbot.lastWord = "";
            } else {
                Qbot.lastWord = event.getMessage();
            }
        }
    }
}
