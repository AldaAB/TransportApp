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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodigoFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodigoFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CodigoFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodigoFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static CodigoFragment3 newInstance(String param1, String param2) {
        CodigoFragment3 fragment = new CodigoFragment3();
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
        return inflater.inflate(R.layout.fragment_codigo3, container, false);
    }

    private ViajesViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b1 = view.findViewById(R.id.button4);

        viewModel = new ViewModelProvider(requireActivity()).get(ViajesViewModel.class);

        TextView mostrarRuta = view.findViewById(R.id.mostrarRuta2);
        TextView mostrarNumPasajeros = view.findViewById(R.id.mostrarNumPasajeros);
        TextView totalAPagar = view.findViewById(R.id.totalAPagar);

        mostrarRuta.setText(viewModel.getOrigen() + " - " + viewModel.getDestino());
        mostrarNumPasajeros.setText(String.valueOf(viewModel.getNumPasajeros()));

        String origen = viewModel.getOrigen();
        String destino = viewModel.getDestino();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String currentUserId = currentUser.getUid();

            DatabaseReference viajesRef = FirebaseDatabase.getInstance().getReference()
                    .child("Usuarios")
                    .child(currentUserId)
                    .child("Viajes");

            DatabaseReference rutasRef = FirebaseDatabase.getInstance().getReference().child("Rutas");

            rutasRef.orderByChild("origen").equalTo(origen).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        procesarPrecioYTotal(snapshot);
                        break;  // Solo necesitas procesar el primer resultado
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("TAG", "Error onCancelled: " + error.getMessage());
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewModel.getMontoTotal() > 0) {
                        // Llama al método para guardar en Firebase solo si hay un monto total válido
                        guardarViajeEnFirebase(currentUserId, viewModel);

                        String contenidoQR =
                                "Informacion del viaje." + "\n" +
                                        "Origen: " + viewModel.getOrigen() + "\n" +
                                        "Destino: " + viewModel.getDestino() + "\n" +
                                        "Cantidad de personas: " + viewModel.getNumPasajeros() + "\n" +
                                        "Monto Total: " + viewModel.getMontoTotal();

                        Bundle bundle = new Bundle();
                        bundle.putString("codigoQR", contenidoQR);

                        // Utilizar el NavigationController para navegar al siguiente fragmento
                        Navigation.findNavController(view).navigate(R.id.action_codigoFragment3_to_codigoFragment4);
                    }
                }
            });
        }

    }

    private void procesarPrecioYTotal(DataSnapshot snapshot) {
        String destinoDB = snapshot.child("destino").getValue(String.class);
        Double precio = snapshot.child("precio").getValue(Double.class);

        if (destinoDB != null && precio != null) {
            viewModel.setMontoTotal(precio * viewModel.getNumPasajeros());

            // Obtener la referencia al TextView totalAPagar dentro del método procesarPrecioYTotal
            TextView totalAPagar = getView().findViewById(R.id.totalAPagar);

            // Verificar que totalAPagar no sea nulo antes de asignar el valor
            if (totalAPagar != null) {
                totalAPagar.setText(String.valueOf(viewModel.getMontoTotal()));
            }

            Log.d("TAG", "Total a Pagar: " + viewModel.getMontoTotal());
        }
    }

    private String currentUserId;

    // Método para guardar la información del viaje en la subcolección "Viajes" del usuario
    private void guardarViajeEnFirebase(String userId, ViajesViewModel viewModel) {
        if (userId != null) {
            DatabaseReference viajesRef = FirebaseDatabase.getInstance().getReference()
                    .child("Usuarios")
                    .child(userId)
                    .child("Viajes");

            String nuevoViajeKey = viajesRef.push().getKey();

            ViajesViewModel viaje = new ViajesViewModel();
            viaje.setOrigen(viewModel.getOrigen());
            viaje.setDestino(viewModel.getDestino());
            viaje.setNumPasajeros(viewModel.getNumPasajeros());
            viaje.setMontoTotal(viewModel.getMontoTotal());

            viajesRef.child(nuevoViajeKey).setValue(viaje);
        } else {
            Log.e("TAG", "Error: userId es nulo");
            // Puedes manejar este caso según tus necesidades, por ejemplo, mostrar un mensaje de error.
        }
    }
}