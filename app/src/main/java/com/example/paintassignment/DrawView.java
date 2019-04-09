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

import static android.graphics.Color.colorToHSV;
import static android.graphics.Color.rgb;


public class DrawView extends View implements View.OnTouchListener
{

    PointF point = new PointF();
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Point> redopaint = new ArrayList<Point>();
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
            //points.add(new Point(event.getX(i), event.getY(i), rgb(0,0,139), r));
        }

        invalidate();
        return true;
    }
    //Undo Function
    public void Undo()
    {
        if (points.size()>0)
        {
            redopaint.add(points.get(points.size()-1));
            points.remove(points.size()-1);
            invalidate();
        }
    }
    //Redo Function
    public void Redo() {
        if (redopaint.size() > 0) {
            points.add(redopaint.get(redopaint.size() - 1));
            redopaint.remove(redopaint.size() - 1);
            invalidate();
        }
    }

    //Clear Function
    public void Clear() {
        points.clear();
        invalidate();

    }

    //Clear Function
    public void ChangeColor() {

        points.add(new Point(getX(),getY(),new Random().nextInt(),r));
        invalidate();

    }
}