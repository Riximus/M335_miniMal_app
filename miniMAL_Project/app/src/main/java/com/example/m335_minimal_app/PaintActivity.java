package com.example.m335_minimal_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PaintActivity extends AppCompatActivity {
/*Version1
    private Paint brush = new Paint();
    private Path path = new Path();
*/
/*Version2
    PaintView paintView = null;
    LinearLayout lilay = null;
    boolean isPainting = false;
    int backgroundColor = 0xff000000;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaintView paintView = new PaintView(this);
        setContentView(R.layout.activity_paint);


    }

}

// todo Version2
           /* Display display = getWindowManager().getDefaultDisplay();
        final float displayWidth = (float)display.getWidth();
        final float displayHeight = (float)display.getHeight();

        paintView = new PaintView(this, display.getWidth(), display.getHeight());
        paintView.changeBackgroundColor(backgroundColor);
        paintView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        paintView.startPaint((int) motionEvent.getX(), (int) motionEvent.getY());
                        isPainting = true;
                        return true;

                    case MotionEvent.ACTION_UP:
                        isPainting = false;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        if(isPainting){
                            paintView.continuePaint((int) motionEvent.getX(), (int) motionEvent.getY());
                            return true;
                        }

                }
                return false;
            }
        });
        setContentView(paintView);
        //brushDefault();*/


//todo use maybe old code
   /* private void brushDefault() {
        brush.setAntiAlias(true);
        brush.setColor(Color.rgb(00,200,0)); //51,51,51 / #333333
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(10f);
    }

    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPath(path, brush);
    }*/