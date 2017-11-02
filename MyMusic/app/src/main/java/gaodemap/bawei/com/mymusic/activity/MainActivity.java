package gaodemap.bawei.com.mymusic.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gaodemap.bawei.com.mymusic.R;

public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    private int i=1;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (i==0){

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);

                startActivity(intent);
                finish();
            }
           if (i>0){

               i--;
               handler.postDelayed(runnable,1000);

           }else {

               handler.removeCallbacks(runnable);
           }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(runnable,100);

    }
}
