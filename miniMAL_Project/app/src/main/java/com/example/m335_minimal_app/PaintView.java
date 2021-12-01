package com.example.m335_minimal_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class PaintView extends View {

    public ViewGroup.LayoutParams params;
    private final Path path = new Path();
    private final Paint brush = new Paint();

    public PaintView(Context context) {
        super(context);

        brush.setAntiAlias(true);
        brush.setColor(Color.GREEN);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();

         switch(event.getAction()){
             case MotionEvent.ACTION_DOWN:
                 path.moveTo(touchX,touchY);
                 return true;
             case MotionEvent.ACTION_MOVE:
                 path.lineTo(touchX,touchY);
                 break;
             default:
                 return false;
         }
         postInvalidate();
         return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,brush);
    }
}


//---------------------------------------------------------------------------------
// todo Version 2 PaintView
/*
    Bitmap mergedBitmap = null;
    Canvas mergedCanvas = null;

    Bitmap bitmap = null;
    Canvas canvas = null;
    int backgroundColor = 0xff000000;
    Paint defaultPaint = new Paint();

    private Paint brush = new Paint();
    private Point touchCoor = new Point();
    private Path path = new Path();

    int red = 51 ,green = 230 ,blue = 51; // 51,51,51


    public PaintView(Context context, int width, int height) {
        super(context);
        mergedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mergedCanvas = new Canvas(mergedBitmap);
    }

    public void changeBackgroundColor(int newColor){
        backgroundColor = newColor;
        invalidate();
    }

    public void startPaint(int touchX, int touchY) {
        brush.setAntiAlias(true);
        brush.setColor(Color.rgb(red,green,blue));
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        this.touchCoor.x = touchX;
        this.touchCoor.y = touchY;

        path.moveTo(touchCoor.x, touchCoor.y);
    }

    public void continuePaint(int touchX, int touchY) {
        canvas.drawPath(path,brush);
        this.touchCoor.x = touchX;
        this.touchCoor.y = touchY;
        invalidate();
        // todo control if path can be given in activity
    }

    private void mergeLayers(){
        mergedCanvas.drawColor(backgroundColor);
        mergedCanvas.drawBitmap(mergedBitmap, 0 , 0, defaultPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mergeLayers();
        canvas.drawBitmap(mergedBitmap, 0 , 0, defaultPaint);
    }*/
