package gaodemap.bawei.com.mymusic.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.okhttputils.HttpUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.adapter.Sousulistview;
import gaodemap.bawei.com.mymusic.bean.Gexq;
import gaodemap.bawei.com.mymusic.bean.Song;
import gaodemap.bawei.com.mymusic.bean.Sousu;
import okhttp3.Call;

public class SousuActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.ImageView sousuback;
    private android.widget.ImageView sousu;
    private android.widget.RelativeLayout b1;
    private android.widget.EditText editsousu;
    private android.widget.ListView sousulistview;
    private Sousulistview adapter;
    private ImageView zhen;
    private AnimationDrawable anim;
   private List<Song> list = new ArrayList<Song>();
    //    private Sousu sousu1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sousu);
        this.zhen = (ImageView) findViewById(R.id.zhen);
        this.sousulistview = (ListView) findViewById(R.id.sousulistview);
        this.editsousu = (EditText) findViewById(R.id.edit_sousu);
        this.b1 = (RelativeLayout) findViewById(R.id.b1);
        this.sousu = (ImageView) findViewById(R.id.sousu);
        this.sousuback = (ImageView) findViewById(R.id.sousuback);

        sousuback.setOnClickListener(this);

        sousu.setOnClickListener(this);

        //开启动画

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {


        switch (v.getId())
        {

            case R.id.sousuback:

                finish();

                break;

            case R.id.sousu:

                list.clear();
                Log.i("xxx","asdas");


                String trim = editsousu.getText().toString().trim();

                if(trim!=null){

                    zhen.setVisibility(View.VISIBLE);
                    anim = (AnimationDrawable)getResources().getDrawable(R.drawable.anim);

                    zhen.setBackground(anim);
                    anim.setOneShot(false);
                    anim.start();
                    String path="http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword="+trim+"&page=1&pagesize=20&showtype=1";

                    HttpUtils.doGet(path, new HttpUtils.GsonObjectCallback<Sousu>() {



                        @Override
                        public void onUi(final Sousu sousu) {

                            adapter=new Sousulistview(sousu,SousuActivity.this);

                            sousulistview.setAdapter(adapter);

                            if(sousu!=null){

                              zhen.setVisibility(View.GONE);

                                list.clear();
                                for(int i=0;i<sousu.getData().getInfo().size();i++){

                                    String path = "http://www.kugou.com/yy/index.php?r=play/getdata&hash=" + sousu.getData().getInfo().get(i).getHash() + "";


                                    HttpUtils.doGet(path, new HttpUtils.GsonObjectCallback<Gexq>() {
                                        @Override
                                        public void onUi(Gexq gexq) {


                                            final Song song=new Song();
                                            song.setPath(gexq.getData().getPlay_url());
                                            song.setSong(gexq.getData().getAudio_name());
                                            song.setSinger(gexq.getData().getAuthor_name());
                                            song.setGeci(gexq.getData().getLyrics());
                                            list.add(song);
//
                                            sousulistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                                    Main2Activity.mBinder.data(list,position);

                                                    Intent intent=new Intent(SousuActivity.this,XqActivity.class);

                                                     intent.putExtra("song", (Serializable) list);
                                                      intent.putExtra("po",position);

                                                    startActivity(intent);


                                                }
                                            });

                                        }
                                        @Override
                                        public void onFailed(Call call, IOException e) {


                                        }
                                    });


                                }



                            }

                        }
                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });


                }else{

                    Toast.makeText(SousuActivity.this,"不能为空",Toast.LENGTH_SHORT).show();

                }


                break;
        }

    }
}
