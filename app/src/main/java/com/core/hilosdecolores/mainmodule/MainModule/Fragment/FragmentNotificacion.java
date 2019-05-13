package com.core.hilosdecolores.mainmodule.MainModule.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.Categoria;
import com.core.hilosdecolores.mainmodule.Notificacion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adaptadores.AdaptadorCategoria;
import adaptadores.AdaptadorNotificacion;

public class FragmentNotificacion extends Fragment {



    private ArrayList<Notificacion> data;

    AdaptadorNotificacion adaptadorNotificacion;
    RecyclerView recyclerView;
    SwipeRefreshLayout refrescador;
    View progresbar;
    SwipeRefreshLayout swipeRefreshLayout;


    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;
    public FragmentNotificacion(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reciclador, container, false);
        recyclerView =  (RecyclerView)view.findViewById(R.id.reciclador);

        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        GetDataNotify();

        return view;
    }


    public void GetDataNotify(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("notificaciones");

        data = new ArrayList<Notificacion>();

        referencia = FirebaseDatabase.getInstance().getReference().child("notificaciones");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<Notificacion>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    Notificacion p = dataSnapshot1.getValue(Notificacion.class);
                    data.add(p);


                }

                adaptadorNotificacion = new AdaptadorNotificacion(data);
                recyclerView.setAdapter(adaptadorNotificacion);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
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
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }




}


