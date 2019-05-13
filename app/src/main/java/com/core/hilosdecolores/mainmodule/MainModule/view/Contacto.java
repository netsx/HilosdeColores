package com.core.hilosdecolores.mainmodule.MainModule.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.MainMenu;

import common.Dialogo;

public class Contacto extends AppCompatActivity {

    private Button button;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
         fab = findViewById(R.id.fab);
        button= findViewById(R.id.button_contacto);



        getInternet();




    }
    public void getInternet(){

        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            initWebView();

        }else {

            Menssage(getResources().getString(R.string.conexion_title),
                    getResources().getString(R.string.conexion_message));


        }

    }

    private void initWebView() {

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(getResources().getString(R.string.ubicacion));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getResources().getString(R.string.email_contact), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permissionCall();

            }
        });

    }

    public void permissionCall(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
            ActivityCompat.requestPermissions(Contacto.this, new String[]{Manifest.permission.CALL_PHONE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
            callPhone();
        }
    }

    public void callPhone() {

        String phone = getString(R.string.telephone);
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }
    public void Menssage(String title,String message){
        FragmentManager fm = getSupportFragmentManager();
        Dialogo alertDialog = Dialogo.newInstance(title, message);

        alertDialog.show(fm, "fragment_alert");

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        finish();
    }
}
