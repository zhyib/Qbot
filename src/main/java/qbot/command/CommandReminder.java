package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import qbot.Qbot;
import qbot.util.TimeSet;

import java.util.ArrayList;

public class CommandReminder implements EverywhereCommand {
    private static final String USAGE = "";

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        String[] messages = eventMessage.getMessage().split(" ");
        StringBuilder ret = new StringBuilder();
        if (messages.length <= 1) {
            ret.append("参数错误");
            ret.append(USAGE);
            return ret.toString();
        } else if (messages[1].equals("增加")) {
            String description = "";
            if (messages.length == 4) {
                description = messages[3];
            }

            try {
                String time = messages[2];
                String year = time.substring(0, 4);
                String month = time.substring(4, 6);
                String day = time.substring(6, 8);
                String hour = time.substring(8, 10);
                String minute = time.substring(10, 12);
                TimeSet timeSet = new TimeSet(year, month, day, hour, minute, description);

                Qbot.timeSets.add(timeSet);
                return "提醒已设置";
            } catch (
                    Exception e) {
                System.out.println(e.toString());
            }
            return "未知错误";
        } else if (messages[1].equals("查看")) {
            ret.append("已设置提醒: \n");
            for (TimeSet ts : Qbot.timeSets) {
                ret.append(ts.getLine());
            }
            return ret.toString();
        } else {
            ret.append("参数错误");
            ret.append(USAGE);
            return ret.toString();
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("reminder", "提醒");
    }
}
