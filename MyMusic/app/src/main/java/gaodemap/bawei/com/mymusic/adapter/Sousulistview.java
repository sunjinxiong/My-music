package gaodemap.bawei.com.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.bean.Sousu;

/**
 * dell 孙劲雄
 * 2017/10/13
 * 14:31
 */

public class Sousulistview extends BaseAdapter {

    private Sousu sousu;
    private Context context;

    public Sousulistview(Sousu sousu, Context context) {
        this.sousu = sousu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sousu.getData().getInfo().size();
    }

    @Override
    public Object getItem(int position) {

        return sousu.getData().getInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolde myHolde;

        if(convertView==null){

            myHolde=new MyHolde();

            convertView=View.inflate(context, R.layout.listviewitem,null);

            myHolde.textView= (TextView) convertView.findViewById(R.id.name);
            myHolde.textView2= (TextView) convertView.findViewById(R.id.gename);

            convertView.setTag(myHolde);

        }else {

            myHolde= (MyHolde) convertView.getTag();
        }

        myHolde.textView.setText(sousu.getData().getInfo().get(position).getFilename());
        myHolde.textView2.setText(sousu.getData().getInfo().get(position).getSingername());

        return convertView;
    }

    class MyHolde{

        TextView textView;
        TextView textView2;

    }

}
