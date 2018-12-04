package personlife.gst.com.myapplication1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by smz on 2018/11/21.
 */

public class Mylinear extends LinearLayout {
    private  Context context;
    public Mylinear(Context context) {
        super(context);
        this.context=context;
    }

    public Mylinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public Mylinear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("MylinearLayout...dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("MylinearLayout...onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("MylinearLayout...onTouchEvent");

        Toast.makeText(context, "MylinearLayout...onTouchEvent", Toast.LENGTH_SHORT).show();
        return true;
    }
}
