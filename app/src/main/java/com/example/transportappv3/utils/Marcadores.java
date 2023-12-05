package com.example.transportappv3.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.example.transportappv3.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Marcadores {
    GoogleMap nMap;
    Context context;

    public Marcadores(GoogleMap nMap, Context context) {
        this.nMap = nMap;
        this.context = context;
    }

    public void  addMarkersDefault(){

        uno(19.818959281052916, -97.36048013976847, "uno");
        dos(19.883856190240977, -97.39314269321044, "dos");
        tres(19.878782073625924, -97.38141446125688, "tres");
        cuatro(19.876226592560872, -97.36677370623464, "cuatro");
        cinco(19.837945666688537, -97.35464508668986, "cinco");
        seis(19.857783885888036, -97.36244207100006, "seis");
    }

    public void uno(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

    public void dos(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

    public void tres(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

    public void cuatro(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

    public void cinco(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

    public void seis(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth=140;
        int whith=1651;
        BitmapDrawable uno=(BitmapDrawable)context.getResources().getDrawable(R.drawable.location_map_pin_mark_icon_148684);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos, whith, heigth, false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns)));
    }

}
