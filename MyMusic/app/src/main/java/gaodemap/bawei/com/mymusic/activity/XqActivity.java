package gaodemap.bawei.com.mymusic.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.bean.Flag;
import gaodemap.bawei.com.mymusic.bean.Song;
import gaodemap.bawei.com.mymusic.utils.MusicUtils;
import me.wcy.lrcview.LrcView;

public class XqActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.ImageView ivplaypagebg,iv_prev,iv_play,iv_next;
    private android.widget.TextView tvtitle,tv_total_time;
    private android.widget.TextView tvartist;
    private android.support.v4.view.ViewPager vpplaypage;
    private android.widget.LinearLayout llcontent;
    private int arr;
    public static  SeekBar seekBar;
    private List<Song> song;
    private int po;
    private static me.wcy.lrcview.LrcView geci;

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int tempProgess = msg.arg1;
            int arg2 = msg.arg2;

            seekBar.setProgress(tempProgess);

            geci.updateTime(arg2);


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);

        this.geci = (LrcView) findViewById(R.id.geci);

        this.llcontent = (LinearLayout) findViewById(R.id.ll_content);


        this.tvartist = (TextView) findViewById(R.id.tv_artist);

        this.tvtitle = (TextView) findViewById(R.id.tv_title);

        iv_prev = (ImageView) findViewById(R.id.iv_prev);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        iv_next = (ImageView) findViewById(R.id.iv_next);

        tv_total_time=(TextView) findViewById(R.id.tv_total_time);

        iv_prev.setOnClickListener(this);

        iv_play.setOnClickListener(this);

        iv_next.setOnClickListener(this);

         Main2Activity.mBinder.updatep();

        seekBar= (SeekBar) findViewById(R.id.sb_progress);

        song = (List<Song>) getIntent().getSerializableExtra("song");



        if (song!=null){

          geci.loadLrc(song.get(po).getGeci());

            String geci = song.get(po).getGeci();

            Log.i("geci",geci);

        }

         po = getIntent().getIntExtra("po",0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //调用MainActivity里的进度方法;());

                Main2Activity.mBinder.bindSeek(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {


            }


        });


        this.ivplaypagebg = (ImageView) findViewById(R.id.iv_play_page_bg);


            arr = getIntent().getIntExtra("arr", 0);


        if (Main2Activity.songs != null) {

            tvtitle.setText(Main2Activity.songs.get(arr).getSinger());

            String s = MusicUtils.formatTime(Main2Activity.songs.get(arr).getDuration());

            tv_total_time.setText(s);
        }

        if(song!=null){

            tvtitle.setText(song.get(po).getSinger());
            geci.loadLrc(song.get(po).getGeci());

        }

        iv_play.setImageResource(R.drawable.play_btn_pause_selector);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.iv_prev:

                if(Main2Activity.songs!=null) {

                    if (arr == Main2Activity.songs.size() - 1) {

                        arr = 0;
                    }

                    arr = arr--;
                    Log.i("sjx", arr + "");

                    Main2Activity.mBinder.it(arr);

                    tvtitle.setText(Main2Activity.songs.get(arr).getSinger());

                    Main2Activity.mBinder.bindPlay();

                }

                if(song!=null){
                    if (po==song.size()-1){

                        po=0;
                    }
                    po = po--;
                    Main2Activity.mBinder.it(po);

                    tvtitle.setText(song.get(po).getSinger());

                    Main2Activity.mBinder.bindPlay();
                }



                break;

            case R.id.iv_play:

                boolean b =Main2Activity.mBinder.bindPause();

                EventBus.getDefault().post(new Flag(b));

                if (b){

                    iv_play.setImageResource(R.drawable.play_btn_pause_selector);

                }else {
                    iv_play.setImageResource(R.drawable.play_btn_play_selector);

                }


                break;
            case R.id.iv_next:

                if (Main2Activity.songs!=null) {
                    if (arr == Main2Activity.songs.size() - 1) {

                        arr = 0;
                    }
                    arr = ++arr;

                    Main2Activity.mBinder.it(arr);


                    tvtitle.setText(Main2Activity.songs.get(arr).getSinger());

                    Main2Activity.mBinder.bindPlay();
                }
                if(song!=null){
                    if (po==song.size()-1){

                        po=0;
                    }
                    po=++po;
                    Log.i("sjx", arr + "");

                    Log.i("++++",po+"");

                    Main2Activity.mBinder.it(po);

                    tvtitle.setText(song.get(po).getSinger());

                    geci.loadLrc(song.get(po).getGeci());

                    Main2Activity.mBinder.bindPlay();
                }
                break;



        }

    }




}
