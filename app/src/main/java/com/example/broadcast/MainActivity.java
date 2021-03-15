package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    //version android
    private TextView tvVersionAndroid;
    //bateria
    private TextView tvNivel;
    Bateria bateria;
    //archivo
    private EditText nombreArchivo;
    private Archivo archivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        activity = this;

        bateria = new Bateria();

        registerReceiver(receptor, bateria.getBatteryFilter());
        casteo();

    }

    //Bateria
    BroadcastReceiver receptor = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bateria.setNivelDeBateria(intent);
            tvNivel.setText("Nivel de bateria: " + bateria.getNivelDeBateria() +"%");
        }
    };

    public void casteo(){
        tvVersionAndroid = (TextView) findViewById(R.id.tvVersionAndroid);
        nombreArchivo = (EditText) findViewById(R.id.etNombreArchivo);
        tvNivel = (TextView) findViewById(R.id.tvNivel);

    }


    //BT
    public void habilitarBluetooth(View view){





        Permiso permiso = new Permiso(context, activity);

        Bluetooth bluetooth = new Bluetooth();

        if(bluetooth.habilitarBluetooth(permiso)){
            Toast.makeText(context, "BT", Toast.LENGTH_SHORT).show();

            startActivityForResult(bluetooth.getHabilitarBT(), bluetooth.getCodigo_habilitaBT());
        }

    }

    public void deshabilitarBluetooth(View view){
        Bluetooth bluetooth = new Bluetooth();
        Boolean deshabilitado = bluetooth.deshabilitarBluetooth();
        if(deshabilitado)
            Toast.makeText(context, "bluetooth deshabilitado", Toast.LENGTH_SHORT).show();
        else  Toast.makeText( context, "Ya estaba deshabilitado el bluetooth", Toast.LENGTH_SHORT).show();
    }




    //final bateria
    //Versión SO

    @Override
    protected void onResume() {
        super.onResume();
        Dispositivo dispositivo = new Dispositivo();
        tvVersionAndroid.setText("Versión SO: " + dispositivo.getVersionSO() + " Versión SDK: " + dispositivo.getVersionSDK());

        //instanciar
        String nombre = this.nombreArchivo.getText().toString() + ".txt";
        String informacion = tvNivel.getText().toString();
        archivo = new Archivo(nombre, informacion);
    }

    public void guardarArchivo(View view){
        //Instaciar permiso
        Permiso permiso = new Permiso(context, activity);
        archivo.crearGuardarArchivo(permiso);
    }
}