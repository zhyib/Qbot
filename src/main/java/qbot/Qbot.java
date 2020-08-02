package qbot;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.sender.IcqHttpApi;
import qbot.command.*;
import qbot.component.ChatLog;
import qbot.listener.ListenerChat;
import qbot.listener.ListenerCommand;
import qbot.listener.ListenerCounter;
import qbot.listener.ListenerRepeater;
import qbot.task.Timer;
import qbot.util.TimeSet;

import java.util.ArrayList;
import java.util.Calendar;

public class Qbot {
    public static ArrayList<TimeSet> timeSets = new ArrayList<TimeSet>();
    public static String lastWord = "";
    public int repeatTimes = 0;
    public long lastQ = 0;

    public static void main(String[] args) {
        String arg0 = "";
        if (args.length > 0) {
            arg0 = args[0];
        }

        // 创建机器人对象 ( 传入配置 )
        PicqBotX bot = new PicqBotX(new PicqConfig(31092).setDebug(true));

        // 添加一个机器人账户 ( 名字, 发送URL, 发送端口 )
        bot.addAccount("Bot01", "127.0.0.1", 31091);

        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(
                new ListenerChat(),
                new ListenerCommand(),
                new ListenerRepeater(),
                new ListenerCounter()
        );

        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        bot.enableCommandManager("--", "!", "/", "~", "#");

        // 注册指令, 可以注册多个指令
        bot.getCommandManager().registerCommands(
                new CommandSay(),
                new CommandReminder(),
                new CommandMute(),
                new CommandWs(),
                new CommandDetect(),
                new CommandNews(),
                new CommandHistory(),
                new CommandReader(),
                new CommandWriter(),
                new CommandCmd()
        );

        // 启动机器人, 不会占用主线程
        bot.startBot();

        // 获取账号
        IcqHttpApi icqHttpApi = bot.getAccountManager().getNonAccountSpecifiedApi();

        // 全局
        Timer timer = new Timer(timeSets, icqHttpApi);

        if (arg0.equals("-prod")) {
            // 问好
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(Calendar.HOUR_OF_DAY) <= 18 && calendar.get(Calendar.HOUR_OF_DAY) >= 6) {
                icqHttpApi.sendGroupMsg(166795834, "米娜桑，哦哈哟~");
            } else {
                icqHttpApi.sendGroupMsg(166795834, "米娜桑，空邦哇~");
            }
        }

        System.out.println("Qbot 启动完成");
    }
}