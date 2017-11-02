package gaodemap.bawei.com.mymusic.bean;

import gaodemap.bawei.com.mymusic.Myservice;

/**
 * dell 孙劲雄
 * 2017/10/12
 * 13:47
 */

public class Minder {

    private Myservice.MBinder mBinder;

    public Minder(Myservice.MBinder mBinder) {
        this.mBinder = mBinder;
    }

    public Myservice.MBinder getmBinder() {
        return mBinder;
    }

    public void setmBinder(Myservice.MBinder mBinder) {
        this.mBinder = mBinder;
    }

    @Override
    public String toString() {
        return "Minder{" +
                "mBinder=" + mBinder +
                '}';
    }
}
