package com.devIvan.nba.Load;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.devIvan.nba.NBA.Equipo;
import com.devIvan.nba.NBA.Jugador;
import com.devIvan.nba.MainActivity;
import com.devIvan.nba.R;
import com.devIvan.nba.Utilities.NBA;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadActivity extends AppCompatActivity {
    LottieAnimationView lottie_nba_loading;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        context = this.getApplicationContext();
        final Firebase iFirebase = new Firebase() {
            @Override
            public void onFirebaseLoadSuccess(ArrayList<Equipo> e) {
                Toast.makeText(LoadActivity.this, "Equipos: "+String.valueOf(e.size())+" | " + "Jugadores[0]: "+ String.valueOf(e.get(0).getListaJugadores().size()), Toast.LENGTH_SHORT).show();
                LoadActivity.this.startActivity(new Intent(LoadActivity.context, MainActivity.class));
            }
        };
        lottie_nba_loading = findViewById(R.id.lottie_nba_loading);
        lottie_nba_loading.playAnimation();
        lottie_nba_loading.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottie_nba_loading.pauseAnimation();
                DatabaseReference equipos = FirebaseDatabase.getInstance().getReference("equipos");
                NBA.listEquipo = new ArrayList<>();
                equipos.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data_equipos:dataSnapshot.getChildren()) {
                            Equipo e = new Equipo();
                            e.listaJugadores = new ArrayList<>();
                            e.setNombre(data_equipos.child("nombre").getValue(true).toString());
                            e.setPabellon(data_equipos.child("pabellon").getValue(true).toString());
                            //
                            GenericTypeIndicator<ArrayList<Jugador>> genericTypeIndicator_Jugador = new GenericTypeIndicator<ArrayList<Jugador>>(){};
                            e.setListaJugadores(data_equipos.child("jugadores").getValue(genericTypeIndicator_Jugador));
                            //
                            NBA.listEquipo.add(e);
                        }
                        iFirebase.onFirebaseLoadSuccess(NBA.listEquipo);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
