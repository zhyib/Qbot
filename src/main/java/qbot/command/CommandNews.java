package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import com.alibaba.fastjson.JSON;
import qbot.component.News;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommandNews implements EverywhereCommand {
    private static final String USER_AGENT = "Mozilla/5.0";

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        String ret = "";
        try {
            ret = getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public CommandProperties properties() {
        return new CommandProperties("news", "新闻");
    }

    private String getDetails() throws Exception {
//        URL url = new URL("http://c.3g.163.com/nc/article/list/T1467284926140/0-20.html");
        URL url = new URL("http://c.m.163.com/nc/article/headline/T1348649580692/0-40.html");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // 添加请求类型，请求头
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine = in.readLine();
        inputLine = inputLine.substring(18, inputLine.length() - 1); // JSON去头去尾
//        inputLine = inputLine.substring(46, inputLine.length() - 17); // JSON去头去尾

        System.out.println(inputLine);
        List<News> list = JSON.parseArray(inputLine, News.class);

        StringBuilder sb = new StringBuilder();
        for (News n : list) {
            sb.append(n.getTitle()).append("\n").append(n.getUrl()).append("\n\n");
        }
        return sb.toString();
    }
}
