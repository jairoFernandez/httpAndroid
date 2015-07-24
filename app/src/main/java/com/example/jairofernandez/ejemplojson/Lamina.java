package com.example.jairofernandez.ejemplojson;

import com.orm.SugarRecord;

/**
 * Created by jairo.fernandez on 24/07/2015.
 */
public class Lamina extends SugarRecord<Lamina> {
    String tipoLamina;
    Integer ancho;
    Integer alto;
    Integer valor;
    Integer valorCm;

    public Lamina() {
    }

    public Lamina(String tipoLamina, Integer ancho, Integer alto, Integer valor, Integer valorCm) {
        this.tipoLamina = tipoLamina;
        this.ancho = ancho;
        this.alto = alto;
        this.valor = valor;
        this.valorCm = valorCm;
    }

    public String getTipoLamina() {
        return tipoLamina;
    }

    public void setTipoLamina(String tipoLamina) {
        this.tipoLamina = tipoLamina;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorCm() {
        return valorCm;
    }

    public void setValorCm(Integer valorCm) {
        this.valorCm = valorCm;
    }
}
