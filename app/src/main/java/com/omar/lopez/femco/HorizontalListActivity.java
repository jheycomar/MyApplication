package com.omar.lopez.femco;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HorizontalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);


        if (fragment == null) {

            fragment = new HorizontalListViewFragment();


            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }


    }
}
