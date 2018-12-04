package personlife.gst.com.myapplication1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by smz on 2018/11/21.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> listl;
    private Context context;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> listl, Context context) {
        super(fm);
        this.listl = listl;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return listl.get(position);
    }

    @Override
    public int getCount() {
        return listl.size();
    }
}
