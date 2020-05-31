package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;
import java.util.Random;

public class CommandMute implements EverywhereCommand {
    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        Random random = new Random();
        String[] times = {"1min", "5min", "10min", "30min", "1H", "2H", "6H", "12H", "1Day"};
        return "禁言套餐: " + times[random.nextInt(times.length)];
    }

    public CommandProperties properties() {
        return new CommandProperties("mute", "禁言");
    }

}
