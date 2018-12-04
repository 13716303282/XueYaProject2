package personlife.gst.com.myapplication1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by smz on 2018/11/21.
 */

public class TwoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,null);
        view.findViewById(R.id.viewpage).setVisibility(View.GONE);
        TextView tv_1= view.findViewById(R.id.tv_1);
        tv_1.setText("這是第二個Fragment");
        return view;
    }
}
