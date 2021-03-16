package com.example.broadcast;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

import java.util.function.Function;


public class Bluetooth {
    private BluetoothAdapter BTadaptador;
    private boolean bandera = false;
    private int codigo_habilitaBT = 100;
    private Intent habilitarBT;

    public Bluetooth() {

    }

    public void metodoBandera(){
        if(bandera == false){
            BTadaptador = BluetoothAdapter.getDefaultAdapter();
            bandera = true;
        }
    }

    public Intent getHabilitarBT() {
        return habilitarBT;
    }

    public int getCodigo_habilitaBT() {
        return codigo_habilitaBT;
    }

    //BT
    public Boolean habilitarBluetooth(Permiso permiso){




        metodoBandera();


        if(BTadaptador == null){
            Log.i(null, "No tiene bluetooth");
            Toast.makeText(permiso.getContext(), "El dispositivo no tiene bluetooth ", Toast.LENGTH_SHORT).show();
            BTadaptador.disable();
            return false;
        }



        if (!BTadaptador.isEnabled()){
            permiso.solicitarPermiso(Manifest.permission.BLUETOOTH);

            habilitarBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            return true;
        }else{
            Toast.makeText(permiso.getContext(), "Ya esta habilitado el bluetooth", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public Boolean deshabilitarBluetooth(){
        metodoBandera();
        if(BTadaptador.isEnabled()){
            BTadaptador.disable();
            return  true;
        }
        return false;
    }

}


