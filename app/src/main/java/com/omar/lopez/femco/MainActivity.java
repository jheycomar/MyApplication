package com.omar.lopez.femco;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtnumero;
    private EditText txtweb;
    private ImageButton btnphone;
    private ImageButton btnweb;
    private ImageButton btncamera;
    private Button btn;
    private FloatingActionButton fab;
    private final int phoneCalCode = 100;

    private final String mensage = "hello from the other side";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazamos los controles
        btn = (Button) findViewById(R.id.btn1);
        txtnumero = (EditText) findViewById(R.id.txtLlamar);
        txtweb = (EditText) findViewById(R.id.txtNavergar);
        btnphone = (ImageButton) findViewById(R.id.btnLlamar);
        btnweb = (ImageButton) findViewById(R.id.btnNavegar);
        btncamera = (ImageButton) findViewById(R.id.btnCamera);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenumber = txtnumero.getText().toString();
                if (phonenumber != null) {
                    //comprovar la vercion de android que estamos corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //vercion new android 6 or >
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, phoneCalCode);

                    } else {
                        //vercion antigua android
                        OlderVersions(phonenumber);
                    }
                }

            }

            private void OlderVersions(String phonenumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(MainActivity.this, "you declined the acces", Toast.LENGTH_LONG).show();
                }

            }


        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pasar de activiti a otra
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("llave", mensage);
                startActivity(intent);

            }
        });
    }

    //para generar este metodo escrivir onRequest y tab
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case phoneCalCode:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //comprovar si a sido haceptado
                    if (result == PackageManager.PERMISSION_GRANTED) {

                        String phoneNumber = txtnumero.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(intent);
                     }
                     else {
                         //no consedio su permiso
                         Toast.makeText(MainActivity.this,"you declined the acces",Toast.LENGTH_LONG).show();
                     }

                 }


                 break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    break;
         }
    }

    private  boolean checkPermission(String permision){
       int resul=this.checkCallingOrSelfPermission(permision);
       return resul== PackageManager.PERMISSION_GRANTED;
    }
}
