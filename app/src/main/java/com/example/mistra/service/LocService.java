package com.example.mistra.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocService extends Service implements LocationListener {
    LocationManager locmanager;
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
        return START_NOT_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Au changement de location on affiche les nouvelles coordonnées
        double longi = location.getLongitude();
        double lati = location.getLatitude();

        Log.i("LOC_SERV_UPDATE","L="+longi+" l="+lati);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, "L="+longi+" l="+lati, duration);
        toast.show();
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
