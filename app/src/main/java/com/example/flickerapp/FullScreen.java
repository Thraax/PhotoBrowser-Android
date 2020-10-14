package com.example.flickerapp;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class FullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_full_screen);
    //    Toolbar toolbar = findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);


        Toast.makeText(FullScreen.this , "Screenshot it" , Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        Bundle getImageLink = getIntent().getExtras();
        String imageLink = getImageLink.getString("Link");
        ImageView fullImage = (ImageView) findViewById(R.id.Full_Image);

        Picasso.with(this).load(imageLink)
                .error(R.drawable.place_holder)
                .placeholder(R.drawable.place_holder)
                .into(fullImage);

    }

}


/*


 Bundle getImageLink = getIntent().getExtras();
        String imageLink = getImageLink.getString("Link");
        ImageView fullImage = (ImageView) findViewById(R.id.Full_Image);


        Picasso.with(this).load(imageLink)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(fullImage);

 */