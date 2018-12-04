package com.qc.cn1.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by smz on 2018/11/26.
 */

public class MyView extends View {

    private int width;
    private int height;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //确定好自定义view的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        MeasureSpec.AT_MOST  //最大测量模式  //最大大不过父控件  最小就是本身的内容大小
//        MeasureSpec.EXACTLY  //精准测量模式   40dp match_parent
//        MeasureSpec.UNSPECIFIED  //不确定测量模式    父控件是wrap_content  子空间也是  wrap_content    ScrollerView  嵌套  Listview

         width = getWidth();
        height = getHeight();

    }


    //此方法 是在自定义VG才会有效果   位置的摆放   onlayout 最终调用的事 layout()方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    //怎么画效果  在此处画   canvas  理解为  黑板
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint  paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

         paint.setTextSize(25);
         canvas.drawText("120",12,38,paint);

        canvas.drawLine(50,30,width,30,paint);
        canvas.drawText("70",25,98,paint);
        canvas.drawLine(50,90,width,90,paint);


        canvas.drawLine(50,height-30,width,height-30,paint);

        String[]  str={"凌晨0点","6","中午12点","18","24"};

        int sum_leeen=(width-50);
        int  len=sum_leeen/4;
        int startX=50;
        int  stopX=width-30;

        int  drawTimeY=height-5;
        paint.setColor(Color.RED);
        canvas.drawText(str[0],startX,height-10,paint);
        canvas.drawText(str[1],len+startX,drawTimeY,paint);
        canvas.drawText(str[2],2*len+startX-30,drawTimeY,paint);
        canvas.drawText(str[3],3*len+startX,drawTimeY,paint);
        canvas.drawText(str[4],stopX,drawTimeY,paint);


        Rect bounds = new Rect();

        paint.getTextBounds(str[2], 0, str[2].length(), bounds);

       int text_x= bounds.width();  //字体的长度
//       bounds.centerX();

        int  cirlen=sum_leeen/23;

        paint.setStrokeWidth(4);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cirlen*16+startX,30,10,paint);

        paint.setColor(Color.YELLOW);

        canvas.drawCircle(cirlen*16+startX,90,10,paint);


//        invalidate();  //重新绘制  主线程
//
//        postInvalidate();   //在子线程绘制




    }
}
