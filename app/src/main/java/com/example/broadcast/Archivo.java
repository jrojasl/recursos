package com.example.broadcast;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;

public class Archivo {

    private String nombreArchivo;
    private String informacion;

    public Archivo(String nombreArchivo, String informacion) {
        this.nombreArchivo = nombreArchivo;
        this.informacion = informacion;
    }

    //metodo verificardor del directorio del archivo

    private void crearDirectorio(File file){

        if(!file.exists()){
            file.mkdirs();
        }

    }

    public void crearGuardarArchivo(Permiso permiso){

        File directorio = null;

        permiso.solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Context context = permiso.getContext();

        if(permiso.statusPermiso() || Build.VERSION.SDK_INT < Build.VERSION_CODES.M ){
            try{

                if(Build.VERSION.SDK_INT< Build.VERSION_CODES.P){
                    //menos o igual a P
                    directorio = new File(Environment.getExternalStorageDirectory(), "ArchivoApp");
                    crearDirectorio(directorio);
                    Toast.makeText(context, "Ruta: " + directorio, Toast.LENGTH_LONG).show();
                }else{
                    //Q o posteriores
                    directorio = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), "ArchivoApp");
                    crearDirectorio(directorio);
                    Toast.makeText(context, "Ruta: " + directorio, Toast.LENGTH_LONG).show();
                }

                if(directorio != null){
                    //crear el archivo
                    File file = new File(directorio, nombreArchivo);
                    FileWriter writer = new FileWriter(file);
                    writer.append(informacion);
                    writer.flush();
                    writer.close();
                    Toast.makeText(context, "Se han guardado los datos: " + directorio, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(context, "No se pudo crear el directorio " + directorio, Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                Toast.makeText(context, "Error " + directorio, Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(context, "No hay permiso " + directorio, Toast.LENGTH_LONG).show();

        }

    }

}
