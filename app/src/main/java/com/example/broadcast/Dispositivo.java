package com.example.broadcast;

import android.os.Build;

public class Dispositivo {

    private String versionSO;
    private int versionSDK;

    public Dispositivo() {
        this.versionSO = Build.VERSION.RELEASE;
        this.versionSDK = Build.VERSION.SDK_INT;
    }

    public String getVersionSO() {
        return versionSO;
    }

    public int getVersionSDK() {
        return versionSDK;
    }
}
