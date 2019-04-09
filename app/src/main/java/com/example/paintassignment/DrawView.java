package com.example.paintassignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View implements View.OnTouchListener{

   class PaintCoordinate
   {
       PointF pt;
       float radius;
   }

   ArrayList <PaintCoordinate> points = new ArrayList<PaintCoordinate>();

   public float getRadius()
   {
       return radius;
   }

   public void setRadius(float radius)
   {
       this.radius = radius;
   }
   float radius = 3;

    public DrawView(Context context) {
        super(context);
        setup();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup()
    {
        setOnTouchListener(this);
    }
    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();

        paint.setColor(Color.RED);

        for(PaintCoordinate pc : points)
        {
            canvas.drawCircle(pc.pt.x, pc.pt.y, pc.radius, paint);
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        PaintCoordinate pc = new PaintCoordinate();
        PointF pt = new PointF();
        pt.set( motionEvent.getX(),motionEvent.getY());

        pc.pt = pt;
        pc.radius = radius;

        points.add(pc);

        invalidate();

        return true;
    }

}
