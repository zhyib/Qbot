package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import qbot.component.ChatLog;

import java.util.ArrayList;

public class CommandDetect implements EverywhereCommand {

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        // ZMQ, ZYB, WGQ, WZA
        long[] userId = {827783577L, 2416128865L, 565859867L, 826347380L};
        int[] userCounter = {0, 0, 0, 0};
        double[][] dense = {
                {2, 0, 0, 0.5},
                {0.3, 1.3, 0.3, 0.6},
                {0.8, 0.5, 0, 1.2}
        };
        String[] rank = {"", "", ""};
        String[] name = {"卖萌基佬浓度：", "二刺螈浓度：", "狗比浓度："};

        for (Long l : ChatLog.chatLogQueue) {
            for (int i = 0; i < userCounter.length; i++) {
                if (l == userId[i]) {
                    userCounter[i] += 1 * ( 1.4 - i * 0.1);
                }
            }
        }
        System.out.println();


        // JL, 2CY, GB
        double[] total = {0, 0, 0};
        for (int i = 0; i < total.length; i++) {
            for (int j = 0; j < userCounter.length; j++) {
                total[i] += (dense[i][j] * userCounter[j]) / 10D;
            }
            if (total[i] >= 1) {
                rank[i] = "極高，建议禁言";
            } else if (total[i] >= 0.8) {
                rank[i] = "極高";
            } else if (total[i] >= 0.6) {
                rank[i] = "高";
            } else if (total[i] >= 0.4) {
                rank[i] = "中";
            } else if (total[i] >= 0.2) {
                rank[i] = "低";
            } else {
                rank[i] = "極低";
            }
        }
        StringBuilder ret = new StringBuilder();
        ret.append("检测中...");
        for (int i = 0; i < total.length; i++) {
            ret.append("\n").append(name[i]).append(total[i] * 100).append("%，").append(rank[i]);
        }
        return ret.toString();
    }

    public CommandProperties properties() {
        return new CommandProperties("JL", "检测浓度", "浓度检测");
    }
}