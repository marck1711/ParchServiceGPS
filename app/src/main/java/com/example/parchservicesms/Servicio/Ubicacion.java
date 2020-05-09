package com.example.parchservicesms.Servicio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class Ubicacion {
    private LocationManager locationManager;
    private Context ctx;
    public double latitud;
    public double longitud;

    public Ubicacion(Context context) {
        this.ctx = context;
    }

    public void localicacion() {
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ctx,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1000);
        }
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        //String provider = locationManager.getBestProvider(MejorOpcion(), false);
        String provider = "network";
        //ListaProviders();
        Location loc = locationManager.getLastKnownLocation(provider);
        if (locationManager!= null)
        {
            if(loc != null) {
                latitud = loc.getLatitude();
                longitud = loc.getLongitude();
            }else{
                latitud = 0;
                longitud = 0;
            }
        }
    }


    private void ListaProviders(){
        locationManager = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
        List<String> lista = locationManager.getAllProviders();
        String mejor = locationManager.getBestProvider(MejorOpcion(),false);
        LocationProvider provider = locationManager.getProvider(mejor);
     }

    private Criteria MejorOpcion(){
        Criteria criterio = new Criteria();
        criterio.setAccuracy(Criteria.ACCURACY_FINE);
        criterio.setAltitudeRequired(true);
        return criterio;
    }
}
