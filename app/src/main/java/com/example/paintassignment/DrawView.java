package com.example.paintassignment;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


public class DrawView extends View implements View.OnTouchListener
{

    PointF point = new PointF();
    ArrayList<Point> points = new ArrayList<Point>();
    MainActivity main = new MainActivity();
    private boolean clear;

    public void setRadius(float r)
    {
        this.r = r;
    }

    float r = 50;

    public DrawView(Context context)
    {
        super(context);
        setOnTouchListener(this);
        point.x = 300;
        point.y = 300;
    }

    public DrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setOnTouchListener(this);
        point.x = 300;
        point.y = 300;
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
        point.x = 300;
        point.y = 300;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        for (Point pt : points)
        {
            paint.setColor(pt.colour);
            canvas.drawCircle(pt.x, pt.y, pt.radius, paint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            points.add(new Point(event.getX(i), event.getY(i), new Random().nextInt(), r));
        }

        invalidate();
        return true;
    }
}
