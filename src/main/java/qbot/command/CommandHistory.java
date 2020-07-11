package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import qbot.component.ChatLog;

import java.util.ArrayList;

public class CommandHistory implements EverywhereCommand {

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (String str : ChatLog.charWords) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }

    public CommandProperties properties() {
        return new CommandProperties("history", "查看消息", "查看");
    }
}
