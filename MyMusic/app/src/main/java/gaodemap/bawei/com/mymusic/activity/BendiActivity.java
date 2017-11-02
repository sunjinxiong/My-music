package gaodemap.bawei.com.mymusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import gaodemap.bawei.com.mymusic.Myservice;
import gaodemap.bawei.com.mymusic.bean.Flag;
import gaodemap.bawei.com.mymusic.bean.Minder;
import gaodemap.bawei.com.mymusic.bean.Posi;
import gaodemap.bawei.com.mymusic.utils.MusicUtils;
import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.adapter.LIstviewAapter;
import gaodemap.bawei.com.mymusic.bean.Song;

public class BendiActivity extends AppCompatActivity implements View.OnClickListener {


    private Myservice.MBinder mBinder;
    private Button button;
    private ListView listView;
    private android.widget.ImageView bendiback;
    private android.widget.RelativeLayout b1;
    private ListView listview;
    private List<Song> musicList1;
    private boolean flag;
    private boolean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bendi);

        EventBus.getDefault().register(this);

        this.listview = (ListView) findViewById(R.id.listview);

        this.b1 = (RelativeLayout) findViewById(R.id.b1);

        this.bendiback = (ImageView) findViewById(R.id.bendiback);

        bendiback.setOnClickListener(this);

        listView= (ListView) findViewById(R.id.listview);

        musicList1 = MusicUtils.getMusicList(BendiActivity.this);


       LIstviewAapter aapter=new LIstviewAapter(musicList1,BendiActivity.this,data);


        listView.setAdapter(aapter);



                 Log.i("xxxx",musicList1+"");

                 bendiback.setOnClickListener(this);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                data = Main2Activity.mBinder.data(musicList1, position);

                flag=data;

                EventBus.getDefault().post(new Posi(position));

                EventBus.getDefault().post(musicList1);

            }
        });

    }
    @Override
    public void onClick(View v) {


        switch (v.getId()){


            case R.id.bendiback:


                finish();

                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Flag b){

        flag = b.isFlag();

        Log.i("sjx",flag+"");

    }



}
