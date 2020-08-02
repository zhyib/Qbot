package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import qbot.util.Exception;
import qbot.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class CommandReader implements EverywhereCommand {

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        Log.log(eventMessage, user, s, arrayList);

        if (arrayList.size() != 0) {
            String fileName = arrayList.get(0);

            String path = "./" + fileName + ".txt";
            FileInputStream fileInputStream = null;
            Random random = new Random();

            try {
                // read file
                fileInputStream = new FileInputStream(path);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = null;
                ArrayList<String> strings = new ArrayList<String>();
                while ((line = bufferedReader.readLine()) != null) {
                    strings.add(line);
                }
                fileInputStream.close();

                if (arrayList.size() >= 2) {
                    if (arrayList.get(1).equals("all")) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < strings.size(); i++) {
                            sb.append((i)).append("、").append(strings.get(i)).append("\n");
                        }
                        return sb.toString();
                    } else {
                        // 有下标
                        int num = Integer.parseInt(arrayList.get(1));
                        if (num < 0 || num > strings.size() - 1) {
                            // 检测下标越界
                            return Exception.INDEX_OUT_OF_BOUND(0, strings.size() - 1);
                        } else {
                            // 返回
                            return strings.get(num);
                        }
                    }
                } else {
                    // 没有下标，随机一个
                    return strings.get(random.nextInt(strings.size()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Exception.FILE_NOT_FOUND();
            }
        } else {
            return Exception.ARG_ERROR("#r filename [number]");
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("读", "read", "r");
    }
}
