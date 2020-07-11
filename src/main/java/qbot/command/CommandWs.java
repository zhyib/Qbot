package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;
import java.util.Random;

public class CommandWs implements EverywhereCommand {

    private final String[] dic = {
            "再苦不能苦游戏，再穷不能穷嘴巴",
            "熬夜？怎么还有弟弟熬夜的？丢人玩意",
            "我学习我快乐",
            "学习学个屁",
            "我有钱",
            "等下，我好像撸多了",
            "信仰就是用来背叛的",
            "理性二字，已刻在心间",
            "今天周二，跑不掉了好吧，这波记忆皇帝",
    };

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            Random random = new Random();
            return dic[random.nextInt(dic.length)];
        } else {
            if (Integer.parseInt(arrayList.get(0)) >= dic.length) {
                return "下标越界，范围为0-" + (dic.length - 1);
            } else {
                return dic[Integer.parseInt(arrayList.get(0))];
            }
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("WSYL", "WS语录", "王叔语录", "ws语录");
    }
}
