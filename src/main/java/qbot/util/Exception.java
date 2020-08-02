package qbot.util;

public class Exception {
    public static String COMMON() {
        return "異常発生！";
    }

    public static String FILE_NOT_FOUND() {
        return "你正试图从虚空中读取一个存储文件";
    }

    public static String ARG_ERROR(String str) {
        return "参数不对啦笨蛋！\n" + str;
    }

    public static String INDEX_OUT_OF_BOUND(int lower, int upper) {
        return "下标越界：范围" + lower + "-" + upper;
    }
}
