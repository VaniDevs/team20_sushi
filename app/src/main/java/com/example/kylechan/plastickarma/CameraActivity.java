package com.example.kylechan.plastickarma;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    ImageView imageView;

    //receive activity result and start new activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            Intent resultsActivity = new Intent(CameraActivity.this, ResultActivity.class);
            startActivity(resultsActivity);
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)  ;
        startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        imageView = (ImageView)findViewById(R.id.imageView);

    }
}
