package com.example.goga747.clickme;

import android.content.Context;


import android.os.Bundle;
import android.os.CountDownTimer;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * Created by goga747 on 26.10.2017.
 */

public class FragmentPlay extends Fragment implements View.OnClickListener {

    CountDownTimer timer;

    TextView textCount;

    String CLICK = "CLICK";

    boolean timeOut;

    private int clickCount;
    private int countsPoints;
    private Random random;

    public FragmentPlayLister lister;


    public FragmentPlay() {
        timeOut = false;
        setCountsPoints(1);
        random = new Random();
    }

    interface FragmentPlayLister {
        void gameOver(int clickCount, int countsPoints);
        void gameInterrupted(int idLayout);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            lister = (FragmentPlayLister) context;
        }catch (ClassCastException ext){
            Log.d(CLICK,ext.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        textCount = (TextView) view.findViewById(R.id.count);

        Button btnStart = (Button) view.findViewById(R.id.btn_tup);
        btnStart.setOnClickListener(this);

        startDownTimer();



        return view;
    }

    private void startDownTimer() {
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                displayTime(l);
            }

            @Override
            public void onFinish() {
                timeOut = true;
                lister.gameOver(getClickCount(),getCountsPoints());
                Log.d(CLICK, "onFinish()");

            }
        }.start();
    }


    private void displayTime(long l) {
        TextView textTime = (TextView) getView().findViewById(R.id.show_time);

        Animation anim = AnimationUtils.loadAnimation(getView().getContext(),R.anim.text_alif);
        Log.d(CLICK, String.valueOf(l / 1000));
        if ((l / 1000) < 10) {

            textTime.setText(Html.fromHtml("<u>00:0" + String.valueOf(l / 1000) + "</u>"));
        } else {
            textTime.setText(Html.fromHtml("<u>00:" + String.valueOf(l / 1000) + "</u>"));
        }
        if ((l / 1000) < 4) {
            textTime.setTextColor( getResources().getColor(R.color.colorAccent));
            textTime.startAnimation(anim);
        }
    }

    @Override
    public void onPause() {

        timer.cancel();
        if (!timeOut){
        Log.d(CLICK,"мы зашли не туда");
        lister.gameInterrupted(R.layout.fragment_start);
        }
        super.onPause();
    }

    @Override
    public void onClick(View view) {
//        sound();
        increaseClickCount();
        scoring(getClickCount());
        Log.d(CLICK,String.valueOf(getClickCount())+"   "+String.valueOf(getCountsPoints()));
    }

//    private void sound() {
//        MediaPlayer player = MediaPlayer.create(getContext(),R.raw.push);
//        player.start();
//        player.stop();
//        player.release();
//    }

    private void scoring(int clickCount) {

        double scoring;

        int active = random.nextInt(10)+1 ;


        scoring = Math.sqrt(getCountsPoints() / clickCount * active);
//        Log.d(CLICK,"!!!!!!!!!!!!!!!!"+String.valueOf(getClickCount())+"   "+String.valueOf(getCountsPoints())+"  random"+String.valueOf(active)+"  scoring"+String.valueOf(scoring));
        int newPoints= (int) (getCountsPoints()+scoring);
        setCountsPoints(newPoints);

        displayTotalPoints();
        displayPoints((int)scoring);
    }

    private void displayPoints(int scoring) {
        TextView textScoring = (TextView) getView().findViewById(R.id.scoring);

        textScoring.setText("+" + scoring);


    }

    private void displayTotalPoints() {

        if ((getCountsPoints()/1000)>0) {

            textCount.setText(Html.fromHtml("<u>" + String.valueOf(getCountsPoints()/1000)+"K "+ String.valueOf(getCountsPoints()%1000)+ "</u>"));
        }else{
            textCount.setText(Html.fromHtml("<u>" + String.valueOf(getCountsPoints()) + "</u>"));

        }


    }

    public int getClickCount() {
        return clickCount;
    }

    public void increaseClickCount() {
        this.clickCount++;
    }

    public int getCountsPoints() {
        return countsPoints;
    }

    public void setCountsPoints(int countsPoints) {
        this.countsPoints = countsPoints;
    }
}
