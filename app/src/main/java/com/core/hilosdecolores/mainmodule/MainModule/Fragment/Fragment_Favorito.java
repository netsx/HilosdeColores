package com.core.hilosdecolores.mainmodule.MainModule.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.core.hilosdecolores.R;


public class Fragment_Favorito extends Fragment {


    public Fragment_Favorito() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reciclador, container, false);
    }



}
