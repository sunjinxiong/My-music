package gaodemap.bawei.com.mymusic.bean;

/**
 * dell 孙劲雄
 * 2017/10/12
 * 14:14
 */

public class Posi {

    private int posi;

    public Posi(int posi) {

        this.posi = posi;
    }

    public Posi() {
    }

    public int getPosi() {
        return posi;
    }

    public void setPosi(int posi) {
        this.posi = posi;
    }

    @Override
    public String toString() {
        return "Posi{" +
                "posi=" + posi +
                '}';
    }
}
