package com.example.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.TextView;

public class Bateria {

    IntentFilter batteryFilter;
    private int nivelDeBateria;


    public Bateria() {
        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    }

    public IntentFilter getBatteryFilter() {
        return batteryFilter;
    }

    public int getNivelDeBateria() {
        return nivelDeBateria;
    }

    public void setNivelDeBateria(Intent intent){
        int escala =  intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int nivelActual = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        if(escala == 100){
            nivelDeBateria = nivelActual;
            //tvNivel.setText("Nivel de bateria: " + nivelActual +"%");
        }else{
            int nivel = 0;
            if(nivelActual > 0 && escala > 0){
                nivel = (nivelActual * 100)/escala;
                nivelDeBateria = nivel;
                //tvNivel.setText("Nivel de bateria: " + nivel +"%");

            }
        }
    }


}
