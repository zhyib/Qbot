package qbot;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.sender.IcqHttpApi;
import qbot.command.CommandMute;
import qbot.command.CommandReminder;
import qbot.command.CommandSay;
import qbot.listener.ListenerChat;
import qbot.listener.ListenerCommand;
import qbot.listener.ListenerRepeater;
import qbot.task.Timer;
import qbot.util.TimeSet;

import java.util.ArrayList;

public class Qbot {
    public static ArrayList<TimeSet> timeSets = new ArrayList<TimeSet>();
    public static String lastWord = "";
    public int repeatTimes = 0;
    public long lastQ = 0;

    public static void main(String[] args) {
        // 创建机器人对象 ( 传入配置 )
        PicqBotX bot = new PicqBotX(new PicqConfig(31092).setDebug(true));

        // 添加一个机器人账户 ( 名字, 发送URL, 发送端口 )
        bot.addAccount("Bot01", "127.0.0.1", 31091);

        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(
                new ListenerChat(),
                new ListenerCommand(),
                new ListenerRepeater()
        );

        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        bot.enableCommandManager("--", "!", "/", "~", "#");

        // 注册指令, 可以注册多个指令
        bot.getCommandManager().registerCommands(
                new CommandSay(),
                new CommandReminder(),
                new CommandMute()
        );

        // 启动机器人, 不会占用主线程
        bot.startBot();

        // 获取账号
        IcqHttpApi icqHttpApi = bot.getAccountManager().getNonAccountSpecifiedApi();

        Timer timer = new Timer(timeSets, icqHttpApi);

        System.out.println("Qbot 启动完成");
    }
}