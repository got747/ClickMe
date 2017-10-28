package com.example.goga747.clickme;


import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentStart.FragmentStartLister, FragmentFinish.FragmentFinishLister,
        FragmentPlay.FragmentPlayLister {

    String CLICK = "CLICK";

    private int clickCount;
    private int countsPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment(R.layout.fragment_start);
    }

    public void displayFragment(int idLayout) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = null;


        switch (idLayout) {
            case R.layout.fragment_start:
                Log.d(CLICK, "tut");

                fragment = new FragmentStart();
                break;
            case R.layout.fragment_play:
                fragment = new FragmentPlay();
                break;
            case R.layout.fragment_finish:
                fragment = new FragmentFinish();
                Bundle bundle = new Bundle();
                bundle.putString("evaluation", evaluationGame(getCountsPoints()));
                bundle.putInt("counts", getCountsPoints());
                fragment.setArguments(bundle);
                break;
        }


        transaction.replace(R.id.fragment, fragment);

        transaction.commit();


    }

    @Override
    public void clickStart(int idLayout) {
        displayFragment(idLayout);
    }


    @Override
    public void gameOver(int clickCount, int countsPoints) {

        setClickCount(clickCount != 0 ? clickCount : 1);
        setCountsPoints(countsPoints != 0 ? countsPoints : 1);

        displayFragment(R.layout.fragment_finish);
    }

    @Override
    public void gameInterrupted(int idLayout) {
        displayFragment(idLayout);
    }

    private String evaluationGame(int countsPoints) {


//        Log.d(CLICK, String.valueOf(i));
//        Log.d(CLICK, String.valueOf(mod));

        if (countsPoints > 150) return "не сломай экран";


        if (countsPoints > 130) return "отлично";
        if (countsPoints > 100) {
            return "среднее";

        } else return "не спи";

    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getCountsPoints() {
        return countsPoints;
    }

    public void setCountsPoints(int countsPoints) {
        this.countsPoints = countsPoints;
    }

    @Override
    public void restart(int idLayout) {
        displayFragment(idLayout);

    }

}
