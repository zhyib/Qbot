package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import com.sun.deploy.util.StringUtils;
import qbot.util.Exception;
import qbot.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandCmd implements EverywhereCommand {
    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        Log.log(eventMessage, user, s, arrayList);

        String cmd = "cmd -c " + String.join(" ", arrayList);
        System.out.println(cmd);

        String line = null;
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime(); //得到本程序
        try {
            Process process = runtime.exec(cmd);  //该实例可用来控制进程并获得相关信息
            //获取进程输出流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return Exception.COMMON();
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("CMD", "cmd");
    }
}
