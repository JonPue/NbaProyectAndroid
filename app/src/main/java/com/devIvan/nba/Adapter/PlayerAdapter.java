package com.devIvan.nba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.devIvan.nba.NBA.Jugador;
import com.devIvan.nba.R;
import com.devIvan.nba.Utilities.NBA;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Jugador> listPlayer;

    public PlayerAdapter(Context context, ArrayList<Jugador> listPlayer) {
        this.context = context;
        this.listPlayer = listPlayer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nba_team_players_line,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Player
        Jugador player = listPlayer.get(position);
        // NBA Lottie
        holder.lottie_spinning_basketball.setAnimation("spinning_basketball.json");
        holder.lottie_spinning_basketball.playAnimation();
        // Player Data
        NBA.setDorsalNumberWithLotties(player.getDorsal(),holder.lottie_dorsal_0,holder.lottie_dorsal_1,holder.lottie_dorsal_2);
        holder.text_view_player_name.setText(player.getNombre());
        holder.text_view_player_position.setText(player.getPosicion());
        holder.text_view_player_height.setText(player.getEstatura());
    }

    @Override
    public int getItemCount() {
        return listPlayer != null ? listPlayer.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView lottie_spinning_basketball,lottie_dorsal_0,lottie_dorsal_1,lottie_dorsal_2;
        TextView text_view_player_name,text_view_player_position,text_view_player_height;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottie_spinning_basketball = itemView.findViewById(R.id.lottie_spinning_basketball);
            lottie_dorsal_0 = itemView.findViewById(R.id.lottie_dorsal_0);
            lottie_dorsal_1 = itemView.findViewById(R.id.lottie_dorsal_1);
            lottie_dorsal_2 = itemView.findViewById(R.id.lottie_dorsal_2);
            text_view_player_name = itemView.findViewById(R.id.text_view_player_name);
            text_view_player_position = itemView.findViewById(R.id.text_view_player_position);
            text_view_player_height = itemView.findViewById(R.id.text_view_player_height);
        }
    }
}
