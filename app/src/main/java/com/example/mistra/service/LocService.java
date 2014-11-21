package com.example.mistra.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.mistra.application.MyApplication;

import java.util.Timer;
import java.util.TimerTask;

public class LocService extends Service implements LocationListener {
    LocationManager locmanager;
    private Timer timer;

    public LocService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // On crée un timer pour la mort programmée du service
        timer = new Timer();
        Log.i("LOC_SERV","Service de localisation lancé");
    }

    @Override
    public void onDestroy() {
        // Quand on arrete le service, on se désabonne des providers
        super.onDestroy();
        locmanager.removeUpdates(this);
        Log.i("LOC_SERV", "Service de localisation arrêté");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Au lancement on défini les provider de Location (GPS et Réseau)
        locmanager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(locmanager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0, this);
        if(locmanager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,0,this);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                stopSelf();
            }
        }, 0, 3000);

        return START_NOT_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Au changement de location on affiche les nouvelles coordonnées
        double longi = location.getLongitude();
        double lati = location.getLatitude();

        MyApplication.getInstance().getEventBus().post("L="+longi+" l="+lati);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
