package com.altair.Rybari.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.altair.Rybari.R;
import com.altair.Rybari.payment.view.PaymentAcivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView personal_photo;
    Button finishedRaces, myRaces, onGoing;

    ArrayList<Integer> ads_images = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finishedRaces = findViewById(R.id.finished_races);
        personal_photo = findViewById(R.id.usImage);
        Glide.with(getApplicationContext())
                .load(R.drawable.user)
                .apply(RequestOptions.circleCropTransform())
                .into(personal_photo);
        getImages();
        //set paymnet in finished races btn;
        finishedRaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PaymentAcivity.class);
                startActivity(intent);
            }
        });
    }

    private void getImages() {
        ads_images.add(R.drawable.fish);
        ads_images.add(R.drawable.ads);
        ads_images.add(R.drawable.ads);
        ads_images.add(R.drawable.ads);
        ads_images.add(R.drawable.ads);
        ads_images.add(R.drawable.ads);
        intiliazeRecyclerView();
    }

    private void intiliazeRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.ads);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(ads_images, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}

