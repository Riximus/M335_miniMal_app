package com.example.m335_minimal_app;

import static android.provider.MediaStore.Images.Media.getBitmap;

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

    private static final String TAG = "PaintActivity";

    Display display = getWindowManager().getDefaultDisplay();

    public float getDispWidth() {
        return dispWidth;
    }

    public float getDispHeight() {
        return dispHeight;
    }

    final float dispWidth = (float)display.getWidth();
    final float dispHeight = (float)display.getHeight();

    PaintView paintView = new PaintView(this);
    ImageButton btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_share = findViewById(R.id.btn_share);

        LinearLayout layout = (LinearLayout) findViewById(R.id.vertical_layout);

        paintView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(paintView);
        setContentView(R.layout.activity_paint);

        ButtonShare();
    }

    protected Uri saveImage(Bitmap image) {
        //Bitmap image = paintView.getBitmap();
        //todo gets compressed here
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

    private void ButtonShare(){
        btn_share.setOnClickListener(view -> {
            shareImageUri(saveImage(paintView.getBitmap()));
        });
    }

}