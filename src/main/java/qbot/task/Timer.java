package qbot.task;

import cc.moecraft.icq.sender.IcqHttpApi;
import qbot.util.TimeSet;

import java.util.ArrayList;
import java.util.Calendar;

public class Timer {
    public final Thread thread;
    public final ArrayList<TimeSet> timeSets;

    public Timer(ArrayList<TimeSet> t, final IcqHttpApi icqHttpApi) {
        timeSets = t;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    Calendar calendar = Calendar.getInstance();
                    for (int i = 0; i < timeSets.size(); i++) {
                        if (timeSets.get(i).equalsCal(calendar)) {
                            icqHttpApi.sendGroupMsg(166795834, timeSets.get(i).getDescription());
                            timeSets.remove(i);
                            i--;
                        }
                        System.out.println(timeSets.size());
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
}
