package qbot.word;

import java.util.HashMap;

public class Reply {
    public static String[][][] wordsPair = {
            {{"测试"}, {"ACK"}},
            {{"给我买"}, {"我的呢我的呢"}},
            {{"氪"}, {"氪，都可以氪"}},
            {{"输了"}, {"输了", "输的透彻"}},
            {{"困了", "想睡觉"}, {"该睡了", "WAMTJ", "才八点"}},
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
                    {"等下，我好像撸多了", "熬夜？怎么还有弟弟熬夜的？丢人玩意", "我有钱", "学习学个屁", "我学习我快乐", "再苦不能苦游戏*再穷不能穷嘴巴"}
            }
    };

    public static String[] copyKeyword = {
            "TMDHS", "NB", "GB", "WAMTJ", "？？？", "JY"
    };

    public static String[] avoidKeyword = {
            "GBZY"
    };

    public static HashMap<String, String> idUsername = new HashMap<String, String>() {
        {
            idUsername.put("826347380", "WS");
            idUsername.put("827783577", "MQ");
        }
    };
}
