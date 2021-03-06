package com.example.m335_minimal_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class PaintView extends View {

    public ViewGroup.LayoutParams params;
    private final Path path = new Path();
    private final Paint brush = new Paint();

    private final String TAG = "PaintView";

    Bitmap bitmap = null;
    Canvas canvas = null;

    public PaintView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);

        brush.setAntiAlias(true);
        brush.setColor(Color.rgb(51,51,51));
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //this.setBackgroundColor(Color.MAGENTA);
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

    protected Bitmap createBitmap(){
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);

        return bitmap;
    }
}