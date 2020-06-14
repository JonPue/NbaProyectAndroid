package com.devIvan.nba.Load;

import com.devIvan.nba.NBA.Equipo;

import java.util.ArrayList;

public interface Firebase {
    void onFirebaseLoadSuccess(ArrayList<Equipo> e);
}
