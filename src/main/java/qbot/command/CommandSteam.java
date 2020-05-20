package qbot.command;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import qbot.component.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommandSteam implements EverywhereCommand {
    private static final String USER_AGENT = "Mozilla/5.0";

    public String run(EventMessage eventMessage, User user, String s, ArrayList<String> arrayList) {
        CommandSteam commandSteam = new CommandSteam();
        int appid = 0;
        String details = "";
        if (arrayList.size() == 0) {
            return "Invalid argument";
        }

        StringBuilder name = new StringBuilder(arrayList.get(0));
        for (int i = 1; i < arrayList.size(); i++) {
            name.append(" ").append(arrayList.get(i));
        }

        try {
            appid = commandSteam.getAppid(name.toString());
            details = commandSteam.getDetails(appid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appid == 0) {
            return "Cannot find game: " + name;
        } else {
            return "https://store.steampowered.com/app/" + appid;
        }
    }

    public CommandProperties properties() {
        return new CommandProperties("stm", "查询");
    }

    private int getAppid(String name) throws Exception {
        URL url = new URL("https://api.steampowered.com/ISteamApps/GetAppList/v2/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // 添加请求类型，请求头
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine = in.readLine();
        inputLine = inputLine.substring(19, inputLine.length() - 2); // JSON去头去尾
        List<Game> list = JSON.parseArray(inputLine, Game.class);
        for (Game game : list) {
            if (game.getName().equals(name)) {
                return game.getAppid();
            }
        }
        return 0;
    }

    private String getDetails(int appid) throws MalformedURLException {
        JSONObject jsonObject = null;
        OutputStreamWriter out = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL("http://store.steampowered.com/api/appdetails");
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", USER_AGENT);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 传入参数
            out = new OutputStreamWriter(httpUrlConn.getOutputStream(), "UTF-8");
            out.write("appids=" + appid + "&cc=cn");
            out.flush();
            out.close();

            // 获取数据
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
