package com.example.hoanghiep.t3hnote;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class MainActivity extends Activity {
    private CreateNoteFrg createNoteFrg;
    private ListNoteFrg listNoteFrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showListNoteScreen();
    }


    public void showCreateScreen() {
        if (createNoteFrg == null) {
            createNoteFrg = new CreateNoteFrg();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, createNoteFrg).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

    }

    public void showListNoteScreen() {
        if (listNoteFrg == null) {
            listNoteFrg = new ListNoteFrg();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, listNoteFrg).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
