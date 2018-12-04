package personlife.gst.com.myapplication1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smz on 2018/11/21.
 */

public class FirstFragment extends Fragment {

    private myViewpageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_first,null);


       ViewPager viewpager= view.findViewById(R.id.viewpage);

        List<View>  list=new ArrayList<>();


        for (int i=0;i<4;i++){
            ImageView imageView=new ImageView(getActivity());
            imageView.setImageResource(R.mipmap.img_doctor_question);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(imageView);
        }

        adapter = new myViewpageAdapter(getActivity() ,list);

        viewpager.setAdapter(adapter);


        return view;
    }
}
