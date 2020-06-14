package com.devIvan.nba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.devIvan.nba.Adapter.TeamAdapter;
import com.devIvan.nba.NBA.Equipo;
import com.devIvan.nba.NBA.user;
import com.devIvan.nba.Utilities.NBA;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int MY_REQUEST_CODE = 123;
    List<AuthUI.IdpConfig> providers;
    RecyclerView recycler_view_teams;
    Button btn_google_authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view_teams = findViewById(R.id.recycler_view_teams);
        btn_google_authentication = findViewById(R.id.btn_google_authentication);



        TeamAdapter teamAdapter = new TeamAdapter(this, NBA.listEquipo);
        recycler_view_teams.setHasFixedSize(true);

        recycler_view_teams.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recycler_view_teams.setAdapter(teamAdapter);

        recycler_view_teams.setNestedScrollingEnabled(false); // Important
        //
        providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());

        btn_google_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInOptions();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sal:{

                // todo.exit from application.
                onBackPressed();
            } break;
            case R.id.web:{

                // todo.go to the web site.
            } break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                final DatabaseReference _user = FirebaseDatabase.getInstance().getReference("users/"+user.getUid());

                _user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        NBA.user = new user(user.getUid(),user.getDisplayName(),user.getEmail());
                        if (dataSnapshot.getKey() != null) {
                            // TODO.show if there are any avatar
                            if (_user.child("equipos_favoritos") != null) {
                                for (DataSnapshot equpos_fav : dataSnapshot.child("equipos_favoritos").getChildren()) {
                                    for (Equipo e : NBA.listEquipo) {
                                        if (e.getNombre().equals(equpos_fav.child("nombre").getValue(true).toString())) {
                                            e.setFav(true);
                                        }
                                    }
                                }
                            }
                        } else {
                            _user.child("_email").setValue(user.getEmail());
                            _user.child("_nombre").setValue(user.getDisplayName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.common_google_signin_btn_icon_dark)
                        .build(), MY_REQUEST_CODE
        );
    }
}
