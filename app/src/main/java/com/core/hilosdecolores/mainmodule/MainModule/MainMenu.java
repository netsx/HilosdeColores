package com.core.hilosdecolores.mainmodule.MainModule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.Fragment.FragmentCategoria;
import com.core.hilosdecolores.mainmodule.MainModule.Fragment.FragmentHome;
import com.core.hilosdecolores.mainmodule.MainModule.Fragment.FragmentNotificacion;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import common.Dialogo;

public class MainMenu extends AppCompatActivity {



    private BottomNavigationView navigation;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    fragment = new FragmentHome();
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_dashboard:
                    fragment = new FragmentCategoria();
                    loadFragment(fragment);


                    return true;
                case R.id.navigation_notifications:
                    fragment = new FragmentNotificacion();
                    loadFragment(fragment);

                    return true;
            }
            return false;
        }
    };

    private Fragment fragment;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getInternet();


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if(adRequest.isTestDevice(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
        }

        fragment = new FragmentHome();
        loadFragment(fragment);

        
    }



    private void loadFragment(Fragment fragment) {



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void getInternet(){

        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            initView();

        }else {

            Menssage(getResources().getString(R.string.conexion_title),
                    getResources().getString(R.string.conexion_message));
            navigation.setVisibility(View.INVISIBLE);

        }

    }

    private void initView() {

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#442c2e'>"+ getResources().getString(R.string.app_name)+" </font>"));



        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_cost));


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void Menssage(String title,String message){
        FragmentManager fm = getSupportFragmentManager();
        Dialogo alertDialog = Dialogo.newInstance(title, message);

        alertDialog.show(fm, "fragment_alert");
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


    }

    public void onBackPressed() {
        finish();

        return;
    }

}
