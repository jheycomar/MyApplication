package com.omar.lopez.femco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class cardview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
