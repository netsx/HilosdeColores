package com.core.hilosdecolores.mainmodule.MainModule.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.MainMenu;
import com.core.hilosdecolores.mainmodule.Producto;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adaptadores.AdaptadorCategoria;
import adaptadores.AdaptadorProducto;

public class Galeria extends AppCompatActivity {

    private ArrayList<Producto> data;

    AdaptadorProducto adaptadorProducto;
    private RecyclerView recyclerView;

    private String   id;

    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        MobileAds.initialize(this,
                "ca-app-pub-3468022161217686/5103083529");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3468022161217686/5103083529");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });


        Intent intent = getIntent();

        id = intent.getStringExtra("id");

          if(id == null){

              Intent activity =new Intent(getApplicationContext(), MainMenu.class);
              startActivity(activity);
              Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_id),
                      Toast.LENGTH_LONG);
          }


        FloatingActionButton fab = findViewById(R.id.fab);



        initView();
    }

    public void initView(){

        recyclerView =(RecyclerView)findViewById(R.id.reciclador_galeria);

        recyclerViewOpenDialog();

    }

    private void recyclerViewOpenDialog( ) {



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("producto");

        data = new ArrayList<Producto>();

        referencia = FirebaseDatabase.getInstance().getReference().child("producto").child(id);
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<Producto>();

                if(dataSnapshot.getValue()==null){
                    onBackPressed();
                    Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_id),
                            Toast.LENGTH_LONG);
                }else {
                    Log.d("TAG", "onDataChange: " + dataSnapshot);

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Producto p = dataSnapshot1.getValue(Producto.class);
                        data.add(p);


                    }

                }
                StaggeredGridLayoutManager _sGridLayoutManager=
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);

                adaptadorProducto = new AdaptadorProducto(data,getApplicationContext());
                // reciclador.setAdapter(adaptadorCategorias);

                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                //reciclador.setLayoutManager(layoutManager);
                recyclerView.setLayoutManager(_sGridLayoutManager);
                recyclerView.hasFixedSize();
                recyclerView.setAdapter(adaptadorProducto);
                recyclerView.addOnItemTouchListener(new AdaptadorProducto.RecyclerTouchListener(getApplicationContext(), recyclerView, new AdaptadorProducto.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("images", data);
                        bundle.putInt("position", position);

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                        newFragment.setArguments(bundle);
                        newFragment.show(ft, "slideshow");

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Galeria.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
