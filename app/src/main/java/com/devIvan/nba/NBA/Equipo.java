package com.devIvan.nba.NBA;

import java.util.ArrayList;

public class Equipo {
    public ArrayList<Jugador> listaJugadores;
    private String nombre;
    private String pabellon;
    private boolean fav;

    public Equipo(){

    }

    public Equipo(ArrayList<Jugador> listaJugadores, String nombre, String pabellon) {
        this.listaJugadores = listaJugadores;
        this.nombre = nombre;
        this.pabellon = pabellon;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPabellon() {
        return pabellon;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
