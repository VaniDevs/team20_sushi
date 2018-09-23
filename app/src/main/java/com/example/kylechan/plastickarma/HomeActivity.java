package com.example.kylechan.plastickarma;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    // set the Image view of the Camera
    ImageView imageView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_camera:
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)  ;
                    startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    return true;
                case R.id.navigation_manualInput:
                    Intent manInputIntent = new Intent(HomeActivity.this, ManualInputActivity.class);
                    startActivity(manInputIntent);
                    return true;
                case R.id.navigation_profile:
                    Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                    return true;
            }
            return false;
        }
    };

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//        imageView.setImageBitmap(bitmap);

       if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
           if (resultCode == HomeActivity.RESULT_OK) {
               Intent resultIntent = new Intent(HomeActivity.this, ResultActivity.class);
               startActivity(resultIntent);
           } else if (resultCode == HomeActivity.RESULT_CANCELED) {
               // User cancelled the image capture
           } else {
               // Image capture failed, advise user
           }
       }
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = (ImageView)findViewById(R.id.imageView);
        //mTextMessage = (TextView) findViewById(R.id.message);

        //scrolling image
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
