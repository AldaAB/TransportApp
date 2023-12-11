package com.example.transportappv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

public class CobrarActivity extends AppCompatActivity {
    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobrar);

        barcodeView = findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            // Aquí obtienes el resultado del escaneo
            String contenidoQR = result.getText();
            Log.d("TAG", "Contenido del código QR: " + contenidoQR);

            // Puedes realizar acciones con el contenidoQR, como enviarlo a otra actividad o fragmento
            // Por ejemplo, puedes abrir un nuevo fragmento y pasar el contenidoQR como argumento.

            // Aquí puedes agregar la lógica que necesitas, por ejemplo, cerrar la actividad o navegar a otra pantalla.
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
            // Método opcional para realizar acciones cuando se detectan puntos posibles en el código QR.
        }
    };

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