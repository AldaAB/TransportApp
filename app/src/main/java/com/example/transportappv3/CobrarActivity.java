package com.example.transportappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

public class CobrarActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobrar);

        barcodeView = findViewById(R.id.barcode_scanner);

        // Verificar y solicitar permisos
        checkCameraPermission();

        // Configurar el escaneo continuo
        barcodeView.decodeContinuous(callback);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            // Aquí obtienes el resultado del escaneo
            String contenidoQR = result.getText();
            Log.d("TAG", "Contenido del código QR: " + contenidoQR);

            showQRInfoDialog(contenidoQR);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    private void showQRInfoDialog(String contenidoQR) {
        // Construir el diálogo con la información del código QR
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información del Código QR");
        builder.setMessage(contenidoQR);
        builder.setPositiveButton("OK", null); // Puedes agregar botones adicionales si es necesario

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso no está concedido, solicítalo.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Si el permiso ya está concedido, puedes abrir la cámara.
            barcodeView.resume();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            // Verifica si el permiso fue concedido.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario concedió el permiso. Puedes abrir la cámara.
                barcodeView.resume();
            } else {
                // El usuario no concedió el permiso. Puedes mostrar un mensaje o tomar otras acciones.
                finish(); // O alguna otra acción para manejar la falta de permisos.
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }
}
