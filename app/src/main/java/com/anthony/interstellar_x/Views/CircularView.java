package com.anthony.interstellar_x.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.anthony.interstellar_x.R;

/**
 * Created by anthonyliu on 2016/4/7.
 */
public class CircularView extends ImageView {

    private static final int STROKE_WIDTH = 5;

    private int height;
    private int width;

    private Context context;
    private Paint paint;
    private int color;

    public CircularView(Context context) {
        super(context);
        this.context = context;
        setDefaultColor();
    }

    public CircularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setDefaultColor();
    }

    public CircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setDefaultColor();
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setDefaultColor(){
        this.color = ContextCompat.getColor(context, R.color.colorAccent);
    }

    public void setColor(int color){
        this.color = color;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        canvas.drawCircle(width / 2, height / 2, width / 2, paint);

    }

}
