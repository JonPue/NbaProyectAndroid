package com.devIvan.nba.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devIvan.nba.NBA.Equipo;
import com.devIvan.nba.NBA.Jugador;
import com.devIvan.nba.R;
import com.devIvan.nba.Utilities.NBA;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder{
    ImageView imgBtn_expand,img_team_picture;
    TextView text_view_team_name;
    public MyViewHolderWithoutChild(@NonNull View itemView) {
        super(itemView);
        imgBtn_expand = itemView.findViewById(R.id.imgBtn_expand);
        img_team_picture = itemView.findViewById(R.id.img_team_picture);
        text_view_team_name = itemView.findViewById(R.id.text_view_team_name);
    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder{
    ImageView imgBtn_expand,img_team_picture, imgBtn_fav;
    TextView text_view_team_name;
    ExpandableLinearLayout expandable_layout;
    RecyclerView recycler_view_players;
    public MyViewHolderWithChild(@NonNull View itemView) {
        super(itemView);
        imgBtn_expand = itemView.findViewById(R.id.imgBtn_expand);
        img_team_picture = itemView.findViewById(R.id.img_team_picture);
        text_view_team_name = itemView.findViewById(R.id.text_view_team_name);
        expandable_layout = itemView.findViewById(R.id.expandable_layout);
        recycler_view_players = itemView.findViewById(R.id.recycler_view_players);
        imgBtn_fav = itemView.findViewById(R.id.imgBtn_fav);
    }
}

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public List<Equipo> listTeam;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public TeamAdapter(Context context, List<Equipo> listTeam) {
        this.context = context;
        this.listTeam = listTeam;
        for (int i = 0; i < listTeam.size(); i++){
            expandState.append(i,false);
            for (Jugador j:listTeam.get(i).getListaJugadores()){
                j.setExpandable(true);
            }
            listTeam.get(i).setFav(false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == 0){
            View itemView = LayoutInflater.from(context).inflate(R.layout.nba_line_without_child, parent, false);
            return new MyViewHolderWithoutChild(itemView);
        } else {
            View itemView = LayoutInflater.from(context).inflate(R.layout.nba_line_with_child, parent, false);
            return new MyViewHolderWithChild(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 0:{
                final MyViewHolderWithoutChild viewHolder = (MyViewHolderWithoutChild) holder;
                Equipo team = listTeam.get(position);
                holder.setIsRecyclable(false);
                //
                viewHolder.text_view_team_name.setText(team.getNombre());
                viewHolder.img_team_picture.setImageResource(NBA.findTeamPicture(team.getNombre()));
            } break;
            case 1:{
                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild) holder;
                Equipo team = listTeam.get(position);
                holder.setIsRecyclable(false);
                viewHolder.expandable_layout.setInRecyclerView(true);
                viewHolder.expandable_layout.setExpanded(expandState.get(position));
                viewHolder.expandable_layout.setListener(new ExpandableLayoutListenerAdapter() {
                    @Override
                    public void onPreOpen() {
                        // TODO.get data of this avatar and insert into ArrayLists.
                        changeRotate(viewHolder.imgBtn_expand,0f,180f).start();
                        expandState.put(position,true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.imgBtn_expand,180f,0f).start();
                        expandState.put(position,false);
                    }
                });
                viewHolder.imgBtn_expand.setRotation(expandState.get(position)?180f:0f);
                viewHolder.imgBtn_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.expandable_layout.toggle();
                    }
                });

                viewHolder.img_team_picture.setImageResource(NBA.findTeamPicture(listTeam.get(position).getNombre()));

                if (listTeam.get(position).isFav()){
                    viewHolder.imgBtn_fav.setImageResource(R.drawable.ic_star_yellow_64dp);
                } else {
                    viewHolder.imgBtn_fav.setImageResource(R.drawable.ic_star_black_64dp);
                }

                viewHolder.imgBtn_fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (NBA.user != null) {
                            final DatabaseReference _user = FirebaseDatabase.getInstance().getReference("users/" + NBA.user.getUid());
                            _user.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (listTeam.get(position).isFav()) {
                                        for (DataSnapshot gr : dataSnapshot.child("equipos_favoritos").getChildren()){
                                            if (gr.child("nombre").getValue(true).toString().equals(listTeam.get(position).getNombre())){
                                                _user.child("equipos_favoritos").child(gr.getKey()).setValue(null);
                                            }
                                        }
                                        listTeam.get(position).setFav(false);
                                        viewHolder.imgBtn_fav.setImageResource(R.drawable.ic_star_black_64dp);
                                    } else {
                                        _user.child("equipos_favoritos").push().child("nombre").setValue(listTeam.get(position).getNombre());
                                        listTeam.get(position).setFav(true);
                                        viewHolder.imgBtn_fav.setImageResource(R.drawable.ic_star_yellow_64dp);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }
                });

                viewHolder.text_view_team_name.setText(team.getNombre());
                viewHolder.img_team_picture.setImageResource(NBA.findTeamPicture(team.getNombre()));
                //
                PlayerAdapter playerAdapter = new PlayerAdapter(context,listTeam.get(position).getListaJugadores());
                viewHolder.recycler_view_players.setHasFixedSize(true);

                viewHolder.recycler_view_players.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                viewHolder.recycler_view_players.setAdapter(playerAdapter);

                viewHolder.recycler_view_players.setNestedScrollingEnabled(false); // Important
            } break;

        }
    }

    private ObjectAnimator changeRotate(ImageView imgBtn_show_avatar_information, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgBtn_show_avatar_information,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }


    @Override
    public int getItemCount() {
        return listTeam != null ? listTeam.size() : 0;
    }
}