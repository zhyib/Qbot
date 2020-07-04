package qbot.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.IcqHttpApi;
import qbot.component.ChatLog;

public class ListenerCounter extends IcqListener {
    @EventHandler
    public void listenerCounter(EventGroupMessage event) {
        if (event.getSender().getId() == 826347380L
                || event.getSender().getId() == 565859867L
                || event.getSender().getId() == 2416128865L
                || event.getSender().getId() == 827783577L) {
            if (ChatLog.chatLogNum < 10) {
                ChatLog.chatLogNum++;
                ChatLog.chatLogQueue.add(event.getSender().getId());
            } else {
                ChatLog.chatLogQueue.remove(0);
                ChatLog.chatLogQueue.add(event.getSender().getId());
            }
        }
    }
}
