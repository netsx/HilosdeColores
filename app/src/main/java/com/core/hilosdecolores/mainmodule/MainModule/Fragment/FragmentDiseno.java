package com.core.hilosdecolores.mainmodule.MainModule.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.Categoria;
import com.core.hilosdecolores.mainmodule.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adaptadores.AdaptadorCategoria;
import adaptadores.AdaptadorProducto;


public class FragmentDiseno extends Fragment {



    private ArrayList<Producto> data;

    AdaptadorProducto adaptadorProducto;

    public FragmentDiseno() {
        // Required empty public constructor
    }

    private RecyclerView reciclador;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.reciclador, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);

        if (getArguments() != null) {
            id = getArguments().getString("id");
        }


        return view;
    }

    private void GetDataProduct(String id)
        {

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference referencia = database.getReference("producto");

            data = new ArrayList<Producto>();

            referencia = FirebaseDatabase.getInstance().getReference().child(id);
            referencia.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    data = new ArrayList<Producto>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {

                        Producto p = dataSnapshot1.getValue(Producto.class);
                        data.add(p);

                    }

                    StaggeredGridLayoutManager _sGridLayoutManager=
                            new StaggeredGridLayoutManager(2,
                                    StaggeredGridLayoutManager.VERTICAL);

                    adaptadorProducto = new AdaptadorProducto(data,getContext());
                    reciclador.setLayoutManager(_sGridLayoutManager);
                    reciclador.setAdapter(adaptadorProducto);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }


}
