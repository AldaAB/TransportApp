package com.example.transportappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText correoEdit;
    private EditText passwordEdit;
    private Button buttonEntrar;
    private String Correo = "";
    private String Password = "";
    FirebaseAuth rAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rAuth = FirebaseAuth.getInstance();

        correoEdit = findViewById(R.id.editTextText);
        passwordEdit = findViewById(R.id.editTextText2);
        buttonEntrar = findViewById(R.id.button);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correo = correoEdit.getText().toString();
                Password = passwordEdit.getText().toString();

                if (!Correo.isEmpty() && !Password.isEmpty()){
                    loginUser();
                }
                else {
                    Toast.makeText(MainActivity.this, "Informacion de inicio de sesión faltante", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void  loginUser(){
        rAuth.signInWithEmailAndPassword(Correo, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Principal1.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Inicio de secion incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void entrarRegistro(View view) {
        Intent intent = new Intent(view.getContext(), RegistroActivity.class);
        view.getContext().startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}