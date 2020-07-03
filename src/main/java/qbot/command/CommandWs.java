package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;
import java.util.Random;

public class CommandWs implements EverywhereCommand {

    private final String[] dic = {
            "等下，我好像撸多了",
            "熬夜？怎么还有弟弟熬夜的？丢人玩意",
            "我有钱",
            "学习学个屁",
            "我学习我快乐",
            "再苦不能苦游戏，再穷不能穷嘴巴",
            "信仰就是用来背叛的",
            "理性二字，已刻在心间",
    };

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        Random random = new Random();
        return dic[random.nextInt(dic.length)];
    }

    public CommandProperties properties() {
        return new CommandProperties("WSYL", "WS语录", "王叔语录", "ws语录");
    }
}
