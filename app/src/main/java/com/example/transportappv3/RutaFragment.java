package com.example.transportappv3;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.transportappv3.utils.GPS_controler;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {

    Context context;
    GoogleMap gMap;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Location location;
    GPS_controler gpsTraker;
    FrameLayout map;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RutaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RutaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RutaFragment newInstance(String param1, String param2) {
        RutaFragment fragment = new RutaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        LinearLayout b1 = view.findViewById(R.id.Liner1);

        map = view.findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getParentFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_rutaFragment_to_principalFragment);
            }
        });

       /* gpsTraker = new GPS_controler();
        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager;
        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        request = Volley.newRequestQueue(getActivity().getApplication());*/
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

       /* nMap=googleMap;
        nMap=SetMyLocationEnable(true);
        LatLng we = new LatLng(19.878782073625924, -97.38141446125688);
        nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(we, 10));
        Utils.markersDefault(nMap, getContext().getApplicationContext());
        nMap.setOnMapClickListener(this);
        nMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);*/

        this.gMap = googleMap;

        LatLng mapTeziutlan = new LatLng(19.81897055975494, -97.36040337332157);
        this.gMap.addMarker(new MarkerOptions().position(mapTeziutlan).title("Marcador de Teziutlan"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapTeziutlan));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        /*Utils.coordenadas.setDestinoLat(latLng.latitude);
        Utils.coordenadas.setDestinoLat(latLng.longitude);
        Toast.makeText(RutaFragment.this, "Toque icono para que selecciones", Toast.LENGTH_SHORT).show();*/

    }

}