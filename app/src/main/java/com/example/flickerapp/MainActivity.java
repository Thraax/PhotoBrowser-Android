package com.example.flickerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements GetFlickerJsonData.OnDataAvailable,
        RecycleItemListener.OnRecycleClickListener {
    private static final String TAG = "MainActivity";
    //private static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";
    private FlickerRecyclerViewAdapter mFlickerRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       activateToolbar(false , "Photo Browser");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecycleItemListener(this, recyclerView, this));

        mFlickerRecyclerViewAdapter = new FlickerRecyclerViewAdapter(this, new ArrayList<Photo>());
        recyclerView.setAdapter(mFlickerRecyclerViewAdapter);

        Log.d(TAG, "onCreate: ends");
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume starts");
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String quereyResult = sharedPreferences.getString(FLICKER_QUERY , "");

        if(FLICKER_QUERY.length() > 0){
            GetFlickerJsonData getFlickerJsonData = new GetFlickerJsonData("https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true, this);
            getFlickerJsonData.execute(quereyResult);
        }
        else{

            GetFlickerJsonData getFlickerJsonData = new GetFlickerJsonData("https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true, this);
            getFlickerJsonData.execute("Wallpaper");

        }

        Log.d(TAG, "onResume ends");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_search){
            Intent intent = new Intent(this , SearchActivity.class);
            startActivity(intent);

        }

        Log.d(TAG, "onOptionsItemSelected() returned: returned");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataAvailable(List<Photo> data, DownloadStatus status) {
        Log.d(TAG, "onDataAvailable: starts");
        if(status == DownloadStatus.OK) {
            mFlickerRecyclerViewAdapter.loadNewData(data);

        } else {
            // download or processing failed
            Log.e(TAG, "onDataAvailable failed with status " + status);
        }

        Log.d(TAG, "onDataAvailable: ends");
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: starts");
        Toast.makeText(MainActivity.this, "Press long  tap to view image detail " , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
        Intent intent = new Intent(this , PhotoDetail.class);
        intent.putExtra(PhotoDetail.PHOTO_TRANSFER , mFlickerRecyclerViewAdapter.getPhoto(position) );
        startActivity(intent);

    }


}
