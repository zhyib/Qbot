package command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CommandBilibili implements EverywhereCommand {
    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        String input = arrayList.get(0);
        String result = "";
        try {
            result = java.net.URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "https://search.bilibili.com/all?keyword=" + result;
    }

    public CommandProperties properties() {
        return new CommandProperties("b", "bilibili");
    }
}
