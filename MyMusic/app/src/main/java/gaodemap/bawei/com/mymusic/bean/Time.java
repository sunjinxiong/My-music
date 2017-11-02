package gaodemap.bawei.com.mymusic.bean;

/**
 * dell 孙劲雄
 * 2017/10/13
 * 9:37
 */

public class Time {


    private int time;

    public Time(int time) {
        this.time = time;


    }

    public Time() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time{" +
                "time=" + time +
                '}';
    }
}
