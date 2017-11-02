package gaodemap.bawei.com.mymusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gaodemap.bawei.com.mymusic.activity.Main2Activity;
import gaodemap.bawei.com.mymusic.activity.XqActivity;
import gaodemap.bawei.com.mymusic.bean.Posi;
import gaodemap.bawei.com.mymusic.bean.Song;
import gaodemap.bawei.com.mymusic.bean.Time;

/**
 * dell 孙劲雄
 * 2017/10/12
 * 9:34
 */

public class Myservice extends Service {


    private MediaPlayer mediaPlayer;
    private int duration;

    @Override
    public void onCreate() {

        super.onCreate();

        mediaPlayer=new MediaPlayer();

    }
    private void updatePro() {
        duration = mediaPlayer.getDuration();

        //定时器
        Timer timer = new Timer();
        //定义一个任务
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                //获取当前进度
                int currentPosition = mediaPlayer.getCurrentPosition();

                Message message = new Message();

                //currentPosition/duration-->结果是0
                int tempProgess = currentPosition*100/duration;

                //从拖动进度转换成seekto要用的值: progress*duration/100;

                message.arg1=tempProgess;

                message.arg2=currentPosition;

                XqActivity.handler.sendMessage(message);

            }
        };

        timer.schedule(timerTask, 0, 1000);
    }

    // 播放
    private void play() {
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    /**
     * Service里的 拖动进度条的方法;
     * @param progress
     */
    private void seek(int progress) {

        int temp = progress*duration/100;

        mediaPlayer.seekTo(temp);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("recycler", "onBind");
        MBinder binder = new MBinder();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("recycler", "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 代理模式,外部用service提供的binder,来调用服务中的方法;
     *
     */
    public class MBinder extends Binder {

        private List<Song> path1;

        public void bindPlay() {
            play();

        }

    public boolean bindPause() {

        if(mediaPlayer.isPlaying()){

            pause();

            return false;
        }else {

            play();

            return true;
        }


    }

    /**
     * 内部类中调用Service里的拖动方法;因为service里才有MedaPlayer
     * @param progress
     */
    public void bindSeek(int progress) {
        seek(progress);

    }

    public void it(int po){

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path1.get(po).getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        public boolean data(List<Song> path, final int po) {

                path1 = path;

                it(po);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    it(po+1);

                }
            });

            return true;
        }

        public void updatep(){

            updatePro();


        }

}

}
