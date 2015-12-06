package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Ratan on 7/29/2015.
 */
public class Fragment_recherche extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_recherche,null);
        initUI(v);

        return v;
    }
    private void initUI(View v){
        Button rech =(Button)v.findViewById(R.id.reche);
        rech.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Intent myIntent = new Intent(Fragment_recherche.this.getActivity(), Liste_Recherche.class);
                   startActivity(myIntent);
                }
            });

    }

}
