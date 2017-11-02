package gaodemap.bawei.com.mymusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import gaodemap.bawei.com.mymusic.activity.BendiActivity;
import gaodemap.bawei.com.mymusic.R;

/**
 * dell 孙劲雄
 * 2017/10/11
 * 13:38
 */

public class FragmentA extends Fragment implements View.OnClickListener {


    private RelativeLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmena,null);

        layout= (RelativeLayout) view.findViewById(R.id.r2);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        layout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.r2:

                Intent intent=new Intent(getActivity(), BendiActivity.class);

                startActivity(intent);

                break;



        }


    }
}
