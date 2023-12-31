package com.example.transportappv3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodigoFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodigoFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CodigoFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodigoFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static CodigoFragment2 newInstance(String param1, String param2) {
        CodigoFragment2 fragment = new CodigoFragment2();
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
        return inflater.inflate(R.layout.fragment_codigo2, container, false);
    }

    private ViajesViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b1 = view.findViewById(R.id.button3);

        viewModel = new ViewModelProvider(requireActivity()).get(ViajesViewModel.class);

        Log.d("Fragment2", "ViewModel Origen: " + viewModel.getOrigen() + ", Destino: " + viewModel.getDestino());

        TextView mostrarRuta = view.findViewById(R.id.mostrarRuta1);
        Log.d("Fragment2", "ViewModel Origen: " + (viewModel.getOrigen() != null ? viewModel.getOrigen() : "null") +
                ", Destino: " + (viewModel.getDestino() != null ? viewModel.getDestino() : "null"));
        mostrarRuta.setText(viewModel.getOrigen() + " - " + viewModel.getDestino());
        mostrarRuta.requestLayout();
        mostrarRuta.invalidate();

        b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText cantidadPas = view.findViewById(R.id.cantidadPas);
                    String canPasajeros = cantidadPas.getText().toString().trim();

                    if (canPasajeros.isEmpty()) {
                        return;
                    }

                    int numPasajeros = Integer.parseInt(canPasajeros);
                    viewModel.setNumPasajeros(numPasajeros);

                    // Utilizar el NavigationController para navegar al siguiente fragmento
                    Navigation.findNavController(view).navigate(R.id.action_codigoFragment2_to_codigoFragment3);
                }
            });
        }
}