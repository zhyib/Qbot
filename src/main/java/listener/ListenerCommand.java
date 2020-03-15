package listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.IcqHttpApi;

public class ListenerCommand extends IcqListener {
    @EventHandler
    public void listenerCommand(EventGroupMessage event) {
        IcqHttpApi icqHttpApi = event.getHttpApi();
        if (event.getMessage().equals("!重启")) {
            icqHttpApi.sendGroupMsg(event.getGroupId(), "正在重启...");
            icqHttpApi.setRestartPlugin();
            icqHttpApi.sendGroupMsg(event.getGroupId(), "已重启");
        }
    }
}
