package com.omar.lopez.femco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<String> names;

    public  MyAdapter(Context context, int layout,List<String>names){
        this.context=context;
        this.layout=layout;
        this.names=names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //View Holder pattern (patron View Holder)
       ViewHolder holder;
        if (convertView==null){

            //inflamos la vista que nos a llegado con nuestro layout personalisado
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(this.layout,null);
            holder=new ViewHolder();
            //referenciamos al elemento amodificar y lo remplasamos
            holder.nameTextView= (TextView) convertView.findViewById(R.id.nombre);
            convertView.setTag(holder);
        }
        else{
            //recuperamos el viewholder
            holder=(ViewHolder)convertView.getTag();
        }
       //nos traemos el valor dependiendo de la posicion
        String currentName=names.get(position);
        //referenciamos al elemento amodificar y lo remplasamos
        holder.nameTextView.setText(currentName);
        //devolvemos la vista inflada y modificada con nuestros datos
        return convertView;

    }
    static class  ViewHolder{
        private TextView nameTextView;
    }

}
