package com.example.transportappv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private EditText editNombre;
    private Spinner spinnerSexo; // Cambio de EditText a Spinner
    private EditText editCorreo;
    private EditText editPassword;
    private EditText editUsuario;
    private Spinner spinnerUser;
    private Button BContinue;

    private String nombre = "";
    private String sexo = "";
    private String correo = "";
    private String password = "";
    private String usuarioN = "";
    private String tipoUser = "";

    FirebaseAuth rAuth;
    DatabaseReference rDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        rAuth = FirebaseAuth.getInstance();
        rDatabase = FirebaseDatabase.getInstance().getReference();

        editNombre = findViewById(R.id.editNombre);
        spinnerSexo = findViewById(R.id.spinnerSexo); // Cambio de EditText a Spinner
        editCorreo = findViewById(R.id.editCorreo);
        editPassword = findViewById(R.id.editPassword);
        editUsuario = findViewById(R.id.editUser);
        spinnerUser = findViewById(R.id.spinnerUserType);

        BContinue = findViewById(R.id.buttonRegistry);

        // Configuración del adaptador para el Spinner
        // Crear el adaptador para el Spinner de género
        ArrayAdapter<CharSequence> adapterGenero = ArrayAdapter.createFromResource(
                this,
                R.array.gender_options,
                android.R.layout.simple_spinner_item
        );
        adapterGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Aplicar el adaptador al Spinner de género
        spinnerSexo.setAdapter(adapterGenero);

// Crear el adaptador para el otro Spinner (spinnerUser) con opciones personalizadas
        ArrayAdapter<CharSequence> adapterUser = ArrayAdapter.createFromResource(
                this,
                R.array.gender_options2,
                android.R.layout.simple_spinner_item
        );
        adapterUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Aplicar el adaptador al otro Spinner (spinnerUser)
        spinnerUser.setAdapter(adapterUser);

        spinnerUser.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoUser = parent.getItemAtPosition(0).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        // Maneja la selección del Spinner
        spinnerSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Almacena la opción seleccionada
                sexo = parentView.getItemAtPosition(position).toString();
                }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Maneja la situación en la que no se selecciona nada (puede ser opcional)
            }
        });

        BContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = editNombre.getText().toString();
                correo = editCorreo.getText().toString();
                password = editPassword.getText().toString();
                usuarioN = editUsuario.getText().toString();

                if (!nombre.isEmpty() && !sexo.isEmpty() && !correo.isEmpty() &&
                        !password.isEmpty() && !usuarioN.isEmpty() && !tipoUser.isEmpty()) {
                    if (password.length() >= 6) {
                        RegistrarUsuario();
                    } else {
                        Toast.makeText(RegistroActivity.this, "La contraseña no es válida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistroActivity.this, "Algun campo no tiene valores válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void RegistrarUsuario() {
        rAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("sexo", sexo);
                    map.put("correo", correo);
                    map.put("password", password);
                    map.put("nombreUser", usuarioN);
                    map.put("tipoUsuario", tipoUser);

                    String id = rAuth.getCurrentUser().getUid();

                    rDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(RegistroActivity.this, com.example.transportappv3.MainActivity.class));
                                finish();
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                // Puedes agregar aquí la navegación a otra actividad si lo deseas
                            } else {
                                Toast.makeText(RegistroActivity.this, "Registro incorrecto, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistroActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
