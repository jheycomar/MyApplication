package com.omar.lopez.femco;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtnumero;
    private EditText txtweb;
    private TextInputEditText txtEmail;
    private ImageButton btnEmail;
    private ImageButton btnphone;
    private ImageButton btnweb;
    private ImageButton btncamera;
    private  Button btngrid;
    private Button btn;
    private Button btncarview;
    private FloatingActionButton fab;
    private Button btnyoutube;
    private final int phoneCalCode = 100;
    private final int pictureFromCamera = 50;

    private Button btnreciclerview;
    private final String mensage = "hello from the other side";


    static  final String youtube_api_key="AIzaSyAOmOErfJDyZIm7wJUoytc8OUOqen5i_L0";
    static  final String youtube_video_id="JPXbOZiASyo";
    static  final String youtube_play_list="PLuEZQoW9bRnT5LUI1rQFwu9ujQVIVQTF3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazamos los controles
        btn = (Button) findViewById(R.id.btn1);
        txtnumero = (EditText) findViewById(R.id.txtLlamar);
        txtweb = (EditText) findViewById(R.id.txtNavergar);
        txtEmail=(TextInputEditText) findViewById(R.id.txtEmail);
        btnphone = (ImageButton) findViewById(R.id.btnLlamar);
        btnweb = (ImageButton) findViewById(R.id.btnNavegar);
        btncamera = (ImageButton) findViewById(R.id.btnCamera);
        btnEmail= (ImageButton) findViewById(R.id.btnEmail);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        btngrid= (Button) findViewById(R.id.btngrib);
        btncarview=(Button)findViewById(R.id.btncardView);
        btnreciclerview=(Button)findViewById(R.id.btnreciclerview);
       btnyoutube=(Button)findViewById(R.id.btnyoutube);

        btnyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnreciclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, HorizontalListActivity.class);
                startActivity(intent);
            }
        });
       //click btncardview
        btncarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, cardview.class);
                startActivity(intent);
            }
        });
        // boton de la llamada
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenumber = txtnumero.getText().toString();
                if (phonenumber != null && !phonenumber.isEmpty()) {
                    //comprovar la vercion de android que estamos corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                      // comprovar si a haceptado, no a haceptado, o nunca sele a preguntado
                        if (checkPermission( Manifest.permission.CALL_PHONE)){//ha aceptado
                            Intent i=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phonenumber));
                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) { return; }
                             startActivity(i);
                        }
                        else{//no ha aceptado
                            //a denegado o es la primera vez que se le pregunta
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //vercion new android 6 or >
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, phoneCalCode);
                            }
                            else {
                                //a denegado
                                Toast.makeText(MainActivity.this,"please enable the request permission",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" +getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }

                        }

                    } else {
                        //vercion antigua android
                        OlderVersions(phonenumber);
                    }
                }
                else {
                    Snackbar.make(view, "Please insert a number", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
        //click fab button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,YoutubeActivity.class);
                startActivity(intent);
            }
        });

        //button btngrid
        btngrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,GridActivity.class);
                startActivity(intent);
            }
        });
        // boton de la web
        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=txtweb.getText().toString();
                if (url!=null && !url.isEmpty()){
                    Intent intentweb=new Intent();
                    intentweb.setAction(Intent.ACTION_VIEW);
                    intentweb.setData(Uri.parse("http://"+url));
                    startActivity(intentweb);
                }
            }
        });

        //boton Email
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=txtEmail.getText().toString();
                if (email!=null && !email.isEmpty()){
                    //contactos
                   Intent intentContact=new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people"));//accede a los contactos
                    //Email rapido
                    Intent intentEmailto=new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+email));

                    //Mail Completo
                    Intent intentEmail=new Intent(Intent.ACTION_SEND,Uri.parse(email));//de
                    //intentEmail.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");//para abrir directo con gmail
                    intentEmail.setType("plaint/text");//tipo texto plano
                   // intentEmail.setType("message/rfc822");// tipo texto plano
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT,"Mail's title");//titulo
                    intentEmail.putExtra(Intent.EXTRA_TEXT,"hi there, i love Myform app,but...");//mensage
                    intentEmail.putExtra(Intent.EXTRA_EMAIL,new  String[]{"fernado@gmail.com","jheycomar@gmail.com"});//para
                    startActivity(Intent.createChooser(intentEmail,"Elija cliente de correo"));//con esta linea corre el intent

                    //Telefono 2 sin permisos requeridos
                    Intent intentphone=new  Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+email));
                   // startActivity(intentphone);
                }
            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir camara
                Intent intentCamera=new Intent("android.media.action.IMAGE_CAPTURE");
               // startActivity(intentCamera);esto solo habre solo la camara
                startActivityForResult(intentCamera,pictureFromCamera);//abre la camara y espera una captura de imagen
            }
        });

        //click navigation to new page
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
    //metodo de la camara result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch ( requestCode){


            case pictureFromCamera:
                if (resultCode== Activity.RESULT_OK){
                    String result=data.toUri(0);

                    Toast.makeText(this," the image is this"+result,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"Error not capture image",Toast.LENGTH_LONG).show();
                }
                break;
          default:
         super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //para generar este metodo escrivir onRequest y tab
    //este metodo controla la respuesta de los permisos que de el ususario
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
    // verifica si el usuario a dado permisos ala aplicacion
    private  boolean checkPermission(String permision){
       int resul=this.checkCallingOrSelfPermission(permision);
       return resul== PackageManager.PERMISSION_GRANTED;
    }
}
