package personlife.gst.com.myapplication1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by smz on 2018/11/21.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    private Context context;
    public MyTextView(Context context) {
        super(context);
        this.context=context;
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("MyTextView...dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("MyTextView...onTouchEvent");
        Toast.makeText(context, "MyTextView...onTouchEvent", Toast.LENGTH_SHORT).show();
        return true;
    }
}
