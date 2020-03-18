package component;

public class Game {
    private int appid;
    private String name;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(int appid, String name) {
        this.appid = appid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "appid=" + appid +
                ", name='" + name + '\'' +
                '}';
    }
}
