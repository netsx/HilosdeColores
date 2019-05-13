package com.core.hilosdecolores;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.hilosdecolores.mainmodule.MainModule.MainMenu;

public class SplashActivity extends AppCompatActivity {


    private ImageView logo;
    private TextView textView;
    private static int splashTimeOut=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=(ImageView)findViewById(R.id.logo);
        textView=(TextView)findViewById(R.id.textView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainMenu.class);
                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.animation);
        textView.startAnimation(myanim);
        logo.startAnimation(myanim);

    }
}
