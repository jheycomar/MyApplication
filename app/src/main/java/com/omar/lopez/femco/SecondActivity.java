package com.omar.lopez.femco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // avilita el la flecha de regreso
       // Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
       // setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView=(TextView) findViewById(R.id.txtsecond);
        //tomar los datos del intent
        Bundle bundle=getIntent().getExtras();
        if (  bundle!=null && bundle.getString("llave")!=null){
        String respu=  bundle.getString("llave");
            textView.setText(respu);
            Toast.makeText(SecondActivity.this,respu,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(SecondActivity.this,"this message is null",Toast.LENGTH_LONG).show();
        }
    }
}
