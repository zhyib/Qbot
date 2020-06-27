package qbot.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.sender.IcqHttpApi;

import java.util.Random;

public class ListenerChat extends IcqListener {
    @EventHandler
    public void autoReply(EventGroupMessage event) {
        IcqHttpApi icqHttpApi = event.getHttpApi();
        Random random = new Random();

        for (String[][] pairs : wordsPair) {
            for (int j = 0; j < pairs[0].length; j++) {
                if (event.getMessage().contains(pairs[0][j])) {
                    System.out.println("AutoReply: " + pairs[0][j]);
                    icqHttpApi.sendGroupMsg(event.getGroupId(), pairs[1][random.nextInt(pairs[1].length)]);
                }
            }
        }
    }

    @EventHandler
    public void wholeReply(EventGroupMessage event) {
        IcqHttpApi icqHttpApi = event.getHttpApi();
        Random random = new Random();

        for (String[][] pairs : wholePair) {
            for (int j = 0; j < pairs[0].length; j++) {
                if (event.getMessage().equals(pairs[0][j])) {
                    System.out.println("WholeReply: " + pairs[0][j]);
                    icqHttpApi.sendGroupMsg(event.getGroupId(), pairs[1][random.nextInt(pairs[1].length)]);
                }
            }
        }
    }

    @EventHandler
    public void copyReply(EventGroupMessage event) {
        String[] wordsCopy = copyKeyword;
        IcqHttpApi icqHttpApi = event.getHttpApi();
        for (String s : wordsCopy) {
            if (event.getMessage().contains(s)) {
                System.out.println("CopyReply: " + s);
                icqHttpApi.sendGroupMsg(event.getGroupId(), event.getMessage());
            }
        }
    }

    @EventHandler
    public void singleReply(EventGroupMessage event) {
        Random random = new Random();
        IcqHttpApi icqHttpApi = event.getHttpApi();
        char head = event.getMessage().charAt(0);
        String follows = event.getMessage().substring(1);
        int index = -1;

        if (head == '夸') {
            index = 0;
        } else if (head == '喷') {
            index = 1;
        }

        for (String s : singleTarget) {
            if (s.equals(follows)) {
                icqHttpApi.sendGroupMsg(event.getGroupId(), follows + singleWord[index][random.nextInt(singleWord[index].length)]);
                break;
            }
        }
    }

    public static String[][][] wordsPair = {
            {{"给我买"}, {"我的呢我的呢~"}},
            {{"氪"}, {"氪，都可以氪"}},
            {{"困了", "想睡觉"}, {"该睡了", "WAMTJ", "才八点！","睡吧睡吧，哦呀斯密~"}},
            {
                    {"想摸鱼", "摸了"},
                    {"该摸了", "摸，摸tmd", "不准摸！“奋斗本来就是一种幸福！”", "不准摸！“奋斗的人生才能称得上有意义的人生！”"}
            },
            {
                    {"吃什么"},
                    {"六点半盖码饭", "热卤", "10元吃屎", "重庆", "13.5", "壮阳套餐", "骨气", "一粒米", "卤味套餐", "马玉涛", "杨国福", "麦咭堡", "优客", "桂林米粉", "牛杂皇", "津市牛肉粉", "杭州小笼包", "KFC", "M记", "多一点", "大小屋", "10元三样", "新概念", "沙县", "左边右边", "四食堂", "百烧！", "盐酥鸡"}
            },
            {
                    {"WS语录", "王叔语录"},
                    {"等下，我好像撸多了", "熬夜？怎么还有弟弟熬夜的？丢人玩意", "我有钱", "学习学个屁", "我学习我快乐", "再苦不能苦游戏，再穷不能穷嘴巴", "信仰就是用来背板的"}
            },
    };

    public static String[][][] wholePair = {
            {{"测试"}, {"ACK"}},
            {{"输了"}, {"输了", "输的透彻"}},
    };

    public static String[] copyKeyword = {
            "TMDHS", "NB", "GB", "WAMTJ", "？？？", "JY",
    };

    public static String[][] singleWord = {
            {"NB", "天秀"},
            {"GB", "SJL", "爬"},
    };

    public static String[] singleTarget = {
            "WS", "MQ", "XD", "GQ", "ZY",   
    };
}
