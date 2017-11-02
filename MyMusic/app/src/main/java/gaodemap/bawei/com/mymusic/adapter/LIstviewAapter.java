package gaodemap.bawei.com.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gaodemap.bawei.com.mymusic.R;
import gaodemap.bawei.com.mymusic.bean.Song;

/**
 * dell 孙劲雄
 * 2017/10/10
 * 20:20
 */

public class LIstviewAapter extends BaseAdapter {

    private List<Song> list;
    private Context context;
    private boolean flag;

    public LIstviewAapter(List<Song> list, Context context, boolean flag) {
        this.list = list;
        this.context = context;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Myview myview;

        if(convertView==null){

            myview=new Myview();

            convertView=View.inflate(context, R.layout.listviewitem,null);

            myview.textView= (TextView) convertView.findViewById(R.id.name);
            myview.textView2= (TextView) convertView.findViewById(R.id.gename);
            myview.imageView= (ImageView) convertView.findViewById(R.id.laba);

            convertView.setTag(myview);

        }else {

            myview= (Myview) convertView.getTag();
        }

        myview.textView.setText(list.get(position).getSong());
        myview.textView2.setText(list.get(position).getSinger());
        if(flag) {
            myview.imageView.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class Myview{

        TextView textView;
        TextView textView2;
        ImageView imageView;

    }
}
