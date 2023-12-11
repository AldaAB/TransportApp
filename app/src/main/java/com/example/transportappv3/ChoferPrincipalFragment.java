package com.example.transportappv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChoferPrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoferPrincipalFragment extends Fragment {

    private FirebaseAuth rAuth;
    private DatabaseReference mDataBase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChoferPrincipalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChoferPrincipalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChoferPrincipalFragment newInstance(String param1, String param2) {
        ChoferPrincipalFragment fragment = new ChoferPrincipalFragment();
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
        return inflater.inflate(R.layout.fragment_chofer_principal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView userText = view.findViewById(R.id.mostrarNombreC);

        rAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        FirebaseUser user = rAuth.getCurrentUser();

        if (user != null){
            String userID = user.getUid();
            mostrarNombre(userID, userText);
        }

        LinearLayout b1 = view.findViewById(R.id.Liner20);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para navegar a otra Activity
                Intent intent = new Intent(getActivity(), CobrarActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
                // Iniciar la Activity
                startActivity(intent);

            }

        });
    }

    private void mostrarNombre(String userID, TextView textViewName){
        DatabaseReference userRef = mDataBase.child(userID);

        userRef.child("nombreUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombreUser = dataSnapshot.getValue(String.class);

                    textViewName.setText(nombreUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}