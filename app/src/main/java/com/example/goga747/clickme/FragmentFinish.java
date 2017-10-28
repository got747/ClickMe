package com.example.goga747.clickme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by goga747 on 26.10.2017.
 */

public class FragmentFinish extends Fragment implements View.OnClickListener {

    private static final String CLICK ="CLICK" ;
    public FragmentFinishLister lister;

    interface FragmentFinishLister { void restart(int idLayout);}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            lister = (FragmentFinishLister) context;
        }catch (ClassCastException ext){
            Log.d(CLICK,ext.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String evaluation = null;
        int counts = 0;

        View view = inflater.inflate(R.layout.fragment_finish, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            evaluation = bundle.getString("evaluation","ворона)");
            counts = bundle.getInt("counts",0);
        }

        display(evaluation, counts, view);

        Button btnStart = (Button) view.findViewById(R.id.btn_restart);

        btnStart.setOnClickListener(this);
        return  view;
    }

    private void display(String evaluation, int counts, View view) {
        TextView textEvaluation = (TextView) view.findViewById(R.id.evaluation);
        textEvaluation.setText(Html.fromHtml("<u>" + evaluation + " : "+ String.valueOf(counts)+ "</u>"));
    }

    @Override
    public void onClick(View view) {
        lister.restart(R.layout.fragment_play);

    }
}
