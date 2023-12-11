package com.example.transportappv3;

import androidx.lifecycle.ViewModel;

public class ViajesViewModel extends ViewModel {

    private String origen = "";
    private String destino= "";
    private int numPasajeros = 0;
    private double montoTotal = 0.0;
    public ViajesViewModel(String origen, String destino, int numPasajeros, double montoTotal) {
        this.origen = origen;
        this.destino = destino;
        this.numPasajeros = numPasajeros;
        this.montoTotal = montoTotal;
    }

    public ViajesViewModel() {
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
