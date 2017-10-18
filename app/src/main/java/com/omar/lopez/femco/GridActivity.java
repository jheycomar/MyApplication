package com.omar.lopez.femco;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    List<String> nombres;
    private int counter=0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        // avilita el la flecha de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //enlazamos los controles
         gridView=(GridView) findViewById(R.id.gridView);

        //datos a mostrar
        nombres=new ArrayList<String>();
        nombres.add("fercho");
        nombres.add("alien");
        nombres.add("florencio");
        nombres.add("Ariel");
        nombres.add("Melissa");
        nombres.add("Wendy");
        nombres.add("alien");
        nombres.add("florencio");
        nombres.add("Ariel");
        nombres.add("Melissa");
        nombres.add("Wendy");
        nombres.add("florencio");

        //click en el iten del listView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Snackbar.make(view,""+nombres.get(position),Snackbar.LENGTH_LONG).setAction("Action",null).show();

            }
        });

        //instancia del adaptador
         myAdapter=new MyAdapter(this,R.layout.grid_item,nombres);
        gridView.setAdapter(myAdapter);
        registerForContextMenu(gridView);
    }
   //inflamos el layout de menu de opcioons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.action_barmenu,menu);
        return true;
    }
//manejador de eventos click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId()){
            case R.id.add_item:
                //añadimos nuevo nombre
                this.nombres.add("Adedº"+(counter++));
                //notificamos al adaptador el cambio producido
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
   //click sostenido  inflamos el layout context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(this.nombres.get(info.position));

        menuInflater.inflate(R.menu.context_menu,menu);
    }
   //manejamos eventos click en context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_item:
                //borramos el item
                this.nombres.remove(info.position);
                //notificamos al adaptador del cambio de los productos
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
            return super.onContextItemSelected(item);
        }


    }
}
