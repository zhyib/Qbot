package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import qbot.util.Exception;
import qbot.util.Log;
import qbot.util.Success;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CommandWriter implements EverywhereCommand {

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        Log.log(eventMessage, user, s, arrayList);

        if (arrayList.size() >= 2) {
            try {
                File writeName = new File("./" + arrayList.get(0) + ".txt");
                writeName.createNewFile();
                FileWriter writer = new FileWriter(writeName, true);
                BufferedWriter out = new BufferedWriter(writer);
                out.write(arrayList.get(1));
                out.newLine();
                out.flush();
                return Success.WRITE();
            } catch (IOException e) {
                e.printStackTrace();
                return Exception.COMMON();
            }
        } else {
            return Exception.ARG_ERROR("#w filename string");
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("writer", "写", "w");
    }
}
