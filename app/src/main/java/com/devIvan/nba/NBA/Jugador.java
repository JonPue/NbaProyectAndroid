package com.devIvan.nba.NBA;

public class Jugador {
    private String nombre;
    private int dorsal;
    private String estatura;
    private String posicion;
    private boolean expandable;

    public Jugador(){

    }

    public Jugador(String nombre, int dorsal, String estatura, String posicion, boolean expandable) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.estatura = estatura;
        this.posicion = posicion;
        this.expandable = expandable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
