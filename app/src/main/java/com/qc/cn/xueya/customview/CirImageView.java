package com.qc.cn.xueya.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.qc.cn.xueya.R;

/**
 * Created by smz on 2018/11/12.
 */

public class CirImageView extends android.support.v7.widget.AppCompatImageView {
    public CirImageView(Context context) {
        super(context);
        init(context ,null);
    }

    public CirImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context ,attrs);
    }
    int hight=30;
    int width=30;
    private void init(Context context, AttributeSet attrs) {

        if(attrs!=null){
            TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CirImageView);
            hight= array.getDimensionPixelSize(R.styleable.CirImageView_hight,hight);
            width=array.getDimensionPixelSize(R.styleable.CirImageView_width,width);
        }

    }

    public CirImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context ,attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_4444);
        Paint paint=new Paint();
        paint.setAntiAlias(true); //没有锯齿
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(bitmap,hight,width,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); //取得是交集
        canvas.drawCircle(width/2,hight/2,hight/2,paint);

    }
}
