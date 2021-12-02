package com.example.m335_minimal_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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
import java.io.FileOutputStream;
import java.io.IOException;

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
            shareImageUri(saveImage(paintView.saveSignature()));
            Log.i(TAG, "Share Button works");
        });
    }

    private Uri saveImage(Bitmap image) {
        File imagesFolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagesFolder.mkdirs();
            File file = new File(imagesFolder, "shared_image.png");

            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);

        } catch (IOException e) {
            Log.d(TAG, "IOException while trying to write file for sharing: " + e.getMessage());
        }
        return uri;
    }

    private void shareImageUri(Uri uri){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/png");
        startActivity(intent);
    }




}