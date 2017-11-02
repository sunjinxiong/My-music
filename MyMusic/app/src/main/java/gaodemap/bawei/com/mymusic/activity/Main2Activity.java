package gaodemap.bawei.com.mymusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import gaodemap.bawei.com.mymusic.Myservice;
import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.bean.Flag;
import gaodemap.bawei.com.mymusic.bean.Posi;
import gaodemap.bawei.com.mymusic.bean.Song;
import gaodemap.bawei.com.mymusic.fragment.FragmentA;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    public static  Myservice.MBinder mBinder;
    private ServiceConnection serviceConnection;
    private RelativeLayout layout;
    private ViewPager viewPager;
    private android.widget.RadioButton radiobutton;
    private android.widget.RadioButton radiobutton2;
    private android.widget.RadioButton radiobutton3;
    private android.widget.LinearLayout r1;
    private ViewPager viewpager;
    private RadioButton radiobutto3;
    private android.widget.ImageView ima;
    private android.widget.TextView gename2;
    private android.widget.ImageView start;
    private TextView gname;
    private ImageView next;
    private int posi;
    public static List<Song> songs;
    private ImageView sousu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_main2);
        
        init();

        startService();

        final List<Fragment> list=new ArrayList<>();

        list.add(new FragmentA());
        list.add(new FragmentA());
        list.add(new FragmentA());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){


                    case 0:

                        radiobutton.setChecked(true);

                        break;
                    case 1:

                        radiobutton2.setChecked(true);

                        break;
                    case 2:

                        radiobutton3.setChecked(true);

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radiobutton.setOnClickListener(this);
        radiobutton2.setOnClickListener(this);
        radiobutton3.setOnClickListener(this);
        start.setOnClickListener(this);
        next.setOnClickListener(this);
        ima.setOnClickListener(this);
        sousu.setOnClickListener(this);

    }

    public void init(){
        this.gname = (TextView) findViewById(R.id.gname);
        this.start = (ImageView) findViewById(R.id.start);
        this.gename2 = (TextView) findViewById(R.id.gename2);
        this.ima = (ImageView) findViewById(R.id.ima);
        this.radiobutto3 = (RadioButton) findViewById(R.id.radiobutto3);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.r1 = (LinearLayout) findViewById(R.id.r1);
        this.radiobutton3 = (RadioButton) findViewById(R.id.radiobutto3);
        this.radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
        this.radiobutton = (RadioButton) findViewById(R.id.radiobutton);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        this.sousu = (ImageView) findViewById(R.id.sousu);
        this.next = (ImageView) findViewById(R.id.next);
        this.start = (ImageView) findViewById(R.id.start);
        this.gname = (TextView) findViewById(R.id.gname);
        this.gename2 = (TextView) findViewById(R.id.gename2);
        this.ima = (ImageView) findViewById(R.id.ima);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.r1 = (LinearLayout) findViewById(R.id.r1);
        this.radiobutto3 = (RadioButton) findViewById(R.id.radiobutto3);
        this.radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
        this.radiobutton = (RadioButton) findViewById(R.id.radiobutton);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.radiobutton:
                viewPager.setCurrentItem(0);

                break;
            case R.id.radiobutton2:

                viewPager.setCurrentItem(1);
                break;
            case R.id.radiobutto3:

                viewPager.setCurrentItem(2);

                break;

            case R.id.start:

                boolean b = mBinder.bindPause();


                EventBus.getDefault().post(new Flag(b));

                if(b){

                   start.setImageResource(R.drawable.ic_play_bar_btn_pause);

                }else {

                    start.setImageResource(R.drawable.ic_play_bar_btn_play);

                }


                break;
            case R.id.next:

                if (posi==songs.size()-1){

                    posi=0;
                }
                posi=++posi;
                Log.i("xxx",posi+"");
                mBinder.it(posi);
                ima.setImageBitmap(songs.get(posi).getAlbum_id());
                gename2.setText(songs.get(posi).getSong());
                gname.setText(songs.get(posi).getSinger());
                start.setImageResource(R.drawable.ic_play_bar_btn_pause);
                mBinder.bindPlay();
                break;

            case R.id.ima:

                Intent intent=new Intent(Main2Activity.this,XqActivity.class);

                intent.putExtra("arr",posi);

                startActivity(intent);

                break;

            case R.id.sousu:

                Intent intent1=new Intent(Main2Activity.this,SousuActivity.class);

                startActivity(intent1);

               break;

        }
    }

    public void startService() {
        // 停止服务
        Intent intent = new Intent(this, Myservice.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                // 链接成功后,返回的Binder就是我们在服务里自己返回的bunder;
                mBinder = (Myservice.MBinder) iBinder;

            }
        };
        // 绑定service--->让我们的本activity和service生命周期进行绑定
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onEvent(List<Song> song){

        if (song!=null) {

            Log.i("+++",song+"");

            EventBus.getDefault().post(new Song());
            songs = song;
            ima.setImageBitmap(song.get(posi).getAlbum_id());
            gename2.setText(song.get(posi).getSong());
            gname.setText(song.get(posi).getSinger());
            start.setImageResource(R.drawable.ic_play_bar_btn_pause);
        }
    }


    @Subscribe
    public void onEvent(Posi song){

        if(song!=null) {
            posi = song.getPosi();

        }
    }
}
