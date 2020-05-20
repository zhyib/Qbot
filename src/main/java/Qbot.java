import cc.moecraft.icq.*;
import cc.moecraft.icq.sender.IcqHttpApi;
import command.CommandBaidu;
import command.CommandBilibili;
import command.CommandSay;
import command.CommandSteam;
import listener.ListenerChat;
import listener.ListenerCommand;

public class Qbot
{
    public static void main(String[] args)
    {
        // 创建机器人对象 ( 传入配置 )
        PicqBotX bot = new PicqBotX(new PicqConfig(31092).setDebug(true));

        // 添加一个机器人账户 ( 名字, 发送URL, 发送端口 )
        bot.addAccount("Bot01", "127.0.0.1", 31091);

        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(
                new ListenerChat(),
                new ListenerCommand()
        );

        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        bot.enableCommandManager("--", "!", "/", "~", "#");

        // 注册指令, 可以注册多个指令
        bot.getCommandManager().registerCommands(
                new CommandSay(),
                new CommandBaidu(),
                new CommandBilibili(),
                new CommandSteam()
        );

        // 启动机器人, 不会占用主线程
        bot.startBot();

        // 获取账号
        IcqHttpApi icqHttpApi = bot.getAccountManager().getNonAccountSpecifiedApi();
//        icqHttpApi.sendGroupMsg(166795834, "Qbot 启动完成");
        System.out.println("Qbot 启动完成");
    }
}