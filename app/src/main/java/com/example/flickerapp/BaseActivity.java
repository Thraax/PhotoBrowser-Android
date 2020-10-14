package com.example.flickerapp;

import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    static final String FLICKER_QUERY = "FLICKER_QUERY";
    static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    void activateToolbar(boolean enableHome , String ActivityName){

        Log.d(TAG, "activateToolbar: Starts");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar == null){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(ActivityName);

            if(toolbar != null){

                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();

            }

        }

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }

    }


}
