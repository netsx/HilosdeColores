package com.core.hilosdecolores.mainmodule.MainModule.Fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.Categoria;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adaptadores.AdaptadorCategoria;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategoria extends Fragment {

    private ArrayList<Categoria> data;

    AdaptadorCategoria adaptadorCategorias;
    RecyclerView reciclador;
    SwipeRefreshLayout refrescador;
    View progresbar;
    private RewardedVideoAd rewardedVideoAd;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    public FragmentCategoria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.reciclador, container, false);


        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);

        reciclador.setHasFixedSize(true);

        GetDataHome();


        return view;
    }


    public void GetDataHome(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("categoria");

        data = new ArrayList<Categoria>();


        referencia = FirebaseDatabase.getInstance().getReference().child("categoria");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<Categoria>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                        Categoria p = dataSnapshot1.getValue(Categoria.class);
                        data.add(p);


                   }

                StaggeredGridLayoutManager _sGridLayoutManager=
                                                        new StaggeredGridLayoutManager(2,
                                                        StaggeredGridLayoutManager.VERTICAL);

                adaptadorCategorias = new AdaptadorCategoria(data,getActivity());

                reciclador.setLayoutManager(_sGridLayoutManager);
                reciclador.setAdapter(adaptadorCategorias);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = reciclador.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
           reciclador.getLayoutManager().onRestoreInstanceState(listState);
        }
    }




}


