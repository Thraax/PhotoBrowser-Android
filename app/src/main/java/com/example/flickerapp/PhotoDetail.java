package com.example.flickerapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.cardview.widget.CardView;

public class PhotoDetail extends BaseActivity  {
    private static final String TAG = "PhotoDetail";
    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
         final ImageView PhotoImage = (ImageView) findViewById(R.id.photo_img);


        activateToolbar(true , "Photo Details");

        Intent intent = getIntent();
        final Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);

        CardView cardView = findViewById(R.id.Card);

        cardView.setCardBackgroundColor(Color.parseColor("#e0dede"));

        if(photo != null){

            TextView photoTile = (TextView) findViewById(R.id.photo_title);

            Resources resources = getResources();

            photoTile.setText(resources.getString(R.string.photo_title_text,photo.getTitle() ) );

        //    photoTile.setText("Title: "+photo.getTitle());

            TextView photoTag = (TextView) findViewById(R.id.photo_tags);

            photoTag.setText(resources.getString(R.string.photo_tags_text, photo.getTag()));

            TextView photoAuthor = (TextView) findViewById(R.id.photo_author);

            photoAuthor.setText(resources.getString(R.string.photo_autthor_text , photo.getAuthor()));

         //   photoAuthor.setText("Author: "+photo.getAuthor());

            ImageView photoImage = (ImageView) findViewById(R.id.photo_img);

            Picasso.with(this).load(photo.getLink())
                    .error(R.drawable.place_holder)
                    .placeholder(R.drawable.place_holder)
                    .into(photoImage);

            }



        PhotoImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent FullScreenIntent = new Intent(PhotoDetail.this , FullScreen.class);
                Bundle PhotoTransfer = new Bundle();
                PhotoTransfer.putString("Link",photo.getLink());
                FullScreenIntent.putExtras(PhotoTransfer);
                startActivity(FullScreenIntent);

                return true;
            }
        });


    }
}

