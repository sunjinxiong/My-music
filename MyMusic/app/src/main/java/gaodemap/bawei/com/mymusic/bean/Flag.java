package gaodemap.bawei.com.mymusic.bean;

/**
 * dell 孙劲雄
 * 2017/10/12
 * 15:24
 */

public class Flag {

    private boolean flag=false;

    public Flag(boolean flag) {
        this.flag = flag;
    }

    public Flag() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "flag=" + flag +
                '}';
    }
}

