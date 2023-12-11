package com.example.transportappv3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionFragment extends Fragment {

    private FirebaseAuth rAuth;
    private DatabaseReference mDataBase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionFragment newInstance(String param1, String param2) {
        InformacionFragment fragment = new InformacionFragment();
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
        View view = inflater.inflate(R.layout.fragment_informacion, container, false);


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView userText = view.findViewById(R.id.nombreUserT);
        TextView nombreText = view.findViewById(R.id.nombreT);
        TextView correoText = view.findViewById(R.id.correoT);
        TextView sexoText = view.findViewById(R.id.sexoT);

        rAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        FirebaseUser user = rAuth.getCurrentUser();

        if (user != null){
            String userID = user.getUid();
            mostrarNUser(userID, userText);
            mostrarNombre(userID, nombreText);
            mostrarCorreo(userID, correoText);
            mostrarSexo(userID, sexoText);
        }

        LinearLayout b1 = view.findViewById(R.id.Liner4);

        Log.d("InformacionFragment", "Data loaded successfully");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_informacionFragment_to_principalFragment);
            }
        });
    }

    private void mostrarNUser(String userID, TextView textViewUser){
        DatabaseReference userRef = mDataBase.child(userID);

        userRef.child("nombreUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombreUser = dataSnapshot.getValue(String.class);
                    
                    textViewUser.setText(nombreUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void mostrarNombre(String userID, TextView textViewName){
        DatabaseReference userRef = mDataBase.child(userID);

        userRef.child("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.getValue(String.class);

                    textViewName.setText(nombre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void mostrarCorreo(String userID, TextView textViewCorreo){
        DatabaseReference userRef = mDataBase.child(userID);

        userRef.child("correo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String correo = dataSnapshot.getValue(String.class);

                    textViewCorreo.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void mostrarSexo(String userID, TextView textViewSexo){
        DatabaseReference userRef = mDataBase.child(userID);

        userRef.child("sexo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String sexo = dataSnapshot.getValue(String.class);

                    textViewSexo.setText(sexo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*@Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }*/
}