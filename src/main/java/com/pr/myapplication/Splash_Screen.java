package com.pr.myapplication;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;

    //variables
    Animation topAnime,motobtmAnime ,bottomAnime;
    ImageView image;
    TextView name, moto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Animation

        topAnime = AnimationUtils.loadAnimation(this,R.anim.top_anime);
        bottomAnime = AnimationUtils.loadAnimation(this,R.anim.bottom_anime);
        motobtmAnime = AnimationUtils.loadAnimation(this,R.anim.motobtm_anime);

        //hooks
        image = findViewById(R.id.logo);
        name = findViewById(R.id.branding);
        moto = findViewById(R.id.moto);

        image.setAnimation(topAnime);
        name.setAnimation(bottomAnime);
        moto.setAnimation(motobtmAnime);

        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
               startActivity(intent);
               finish();
            }
        },SPLASH_SCREEN);

    }
}