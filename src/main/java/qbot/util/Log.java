package qbot.util;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Log {
    public static void log(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        System.out.println("MSG: " + eventMessage.message);
        System.out.println("USR: " + user.getId());
        System.out.println("STR: " + s);
        System.out.println("ARG: " + Arrays.toString(arrayList.toArray()));
    }
}
