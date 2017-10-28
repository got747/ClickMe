package com.example.goga747.clickme;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by goga747 on 26.10.2017.
 */

public class FragmentStart extends Fragment implements View.OnClickListener {
    String CLICK="CLICK";

    public FragmentStartLister lister;

    interface FragmentStartLister {
        void clickStart(int idLayout);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            lister = (FragmentStartLister) context;
        }catch (ClassCastException ext){
            Log.d(CLICK,ext.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);

        Button btnStart = (Button) view.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {

        Log.d(CLICK,"TAP FragmentStart");
        lister.clickStart(R.layout.fragment_play);

    }
}
