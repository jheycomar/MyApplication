package com.omar.lopez.femco;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    List<String> nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // avilita el la flecha de regreso
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //enlazamos los controles
        textView=(TextView) findViewById(R.id.txtsecond);
        listView=(ListView)findViewById(R.id.lstView);

        //datos a mostrar
        nombres=new ArrayList<String>();
        nombres.add("fercho");
        nombres.add("alien");
        nombres.add("florencio");
        nombres.add("Ariel");
        nombres.add("Melissa");
        nombres.add("Wendy");
        nombres.add("florencio");
        nombres.add("Ariel");
        nombres.add("Melissa");
        nombres.add("Wendy");

         //la forma visual enque se va a mostrar

      //  ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,nombres);
        //enlazamos nuestro adaptador con nuestro lisView
       // listView.setAdapter(adaptador);

        //tomar los datos del intent de la primera pagina
        Bundle bundle=getIntent().getExtras();
        if (  bundle!=null && bundle.getString("llave")!=null){
        String respu=  bundle.getString("llave");
            textView.setText(respu);
            Toast.makeText(SecondActivity.this,respu,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(SecondActivity.this,"this message is null",Toast.LENGTH_LONG).show();
        }

        //click en el iten del listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Snackbar.make(view,""+nombres.get(position),Snackbar.LENGTH_LONG).setAction("Action",null).show();

            }
        });

        //instancia del adaptador
        MyAdapter myAdapter=new MyAdapter(this,R.layout.list_item,nombres);
        listView.setAdapter(myAdapter);

    }

}

