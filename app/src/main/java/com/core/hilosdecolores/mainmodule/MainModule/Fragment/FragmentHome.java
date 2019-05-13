package com.core.hilosdecolores.mainmodule.MainModule.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.view.Contacto;


public class FragmentHome  extends Fragment   {







    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;
    public FragmentHome(){

    }

    private Button button;
    private WebView myWebView;
    private ImageView imageView;
    private ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);


        imageView=(ImageView)view.findViewById(R.id.image);
        constraintLayout=(ConstraintLayout)view.findViewById(R.id.content);

        Glide.with(this)
                .load("http://www.impormegatex.com/wp-content/uploads/2018/02/home_bajo6-1.png")
                .error(R.drawable.needle)
                .into(imageView);


           button=(Button) view.findViewById(R.id.button_categoria);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                    Intent intent = new Intent(getContext(), Contacto.class);
                    startActivity(intent);
                   getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                //   permissionCall();
               }
           });

        return view;
    }





    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();


    }


/*

    public static FragmentDiseno newInstance(String param1, String param2) {
        FragmentDiseno fragment = new FragmentDiseno();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/

}

