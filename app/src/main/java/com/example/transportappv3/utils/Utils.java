package com.example.transportappv3.utils;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public static Coordenadas coordenadas = new Coordenadas();
    public static List<List<HashMap<String, String>>>route=new ArrayList<>();
    public static void markersDefault(GoogleMap nMap, Context context){
        new Marcadores(nMap, context).addMarkersDefault();
    }

}