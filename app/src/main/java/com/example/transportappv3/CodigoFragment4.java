package com.example.transportappv3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodigoFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodigoFragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CodigoFragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodigoFragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static CodigoFragment4 newInstance(String param1, String param2) {
        CodigoFragment4 fragment = new CodigoFragment4();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigo4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("TAG", "Fragment4 onViewCreated");

        ImageView imageViewCodigoQR = view.findViewById(R.id.imageViewCodigoQR);

        if (getArguments() != null) {
            String contenidoQR = getArguments().getString("codigoQR");

            Log.d("TAG", "Fragment4 recibió el contenido del código QR: " + contenidoQR);;

            // Llama al método para mostrar el código QR
            mostrarCodigoQR(contenidoQR, imageViewCodigoQR);
        }

        Button b1 = view.findViewById(R.id.button5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para navegar a otra Activity
                Intent intent = new Intent(getActivity(), Principal1.class);

                // Iniciar la Activity
                startActivity(intent);
            }
        });
    }

    private void mostrarCodigoQR(String contenidoQR, ImageView imageView) {
        Log.d("TAG", "mostrarCodigoQR: Iniciando método");
        Log.d("TAG", "Contenido del código QR: " + contenidoQR);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(contenidoQR, BarcodeFormat.QR_CODE, 250, 250);
            imageView.setImageBitmap(bitmap);
            Log.d("TAG", "Generación del código QR exitosa");
        }
        catch (Exception e){
            Log.e("TAG", "Error al generar el código QR: " + e.getMessage());
            e.printStackTrace();
        }
    }

}