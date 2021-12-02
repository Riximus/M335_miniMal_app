package com.example.m335_minimal_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    PaintView paintView = null;

    String TAG = "PaintActivity";
    ImageButton btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PaintView paintView = new PaintView(this);
        setContentView(R.layout.activity_paint);

        LinearLayout layout = (LinearLayout) findViewById(R.id.vertical_layout);
        paintView = new PaintView(this);
        paintView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(paintView);

        btn_share = findViewById(R.id.btn_share);
        ButtonShare();
    }

    private void ButtonShare (){
        paintView = new PaintView(this);
        btn_share.setOnClickListener(view -> {

            Log.i(TAG, "------------- SHARE BUTTON PRESSED --------");
            //saveBitmap(paintView.getContext(), paintView.createBitmap());
            Log.i(TAG, "------------- COULD SAVE ----------");
            shareBitmap();

            Log.i(TAG, "------ COULD SHARE -------");
        });
    }

    public static Uri saveBitmap(Context mContext, Bitmap bitmap){

        //String mTimeStamp = new SimpleDateFormat("ddMM_yyyy_HHmm").format(new Date());

        String mImageName = "snap_";

        ContextWrapper wrapper = new ContextWrapper(mContext);

        File file = wrapper.getDir("Images",MODE_PRIVATE);

        file = new File(file, "snap_"+ mImageName+".png");

        try{

            OutputStream stream = null;

            stream = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

            stream.flush();

            stream.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }

        Uri mImageUri = Uri.parse(file.getAbsolutePath());

        return mImageUri;
    }


    protected void shareBitmap() {

        String stringUri = "";
        stringUri = saveBitmap(paintView.getContext(), paintView.createBitmap()).toString();
        Log.i(TAG, stringUri );

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, saveBitmap(paintView.getContext(), paintView.createBitmap()));
        intent.setType("image/jpeg");
        //Intent shareIntent = Intent.createChooser(intent, "Share with :D");
        startActivity(intent);
        }

}