package com.example.excursion.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.excursion.R;
import com.example.excursion.entities.Reservation;
import java.util.List;

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.ViewHolder> {

    private final List<Reservation> reservations;
    private final OnDeleteClickListener deleteClickListener;

    // Constructor now accepts an OnDeleteClickListener to handle delete actions
    public ReservationsAdapter(List<Reservation> reservations, OnDeleteClickListener deleteClickListener) {
        this.reservations = reservations;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.dateText.setText(reservation.getDate());
        holder.timeText.setText(reservation.getTime());
        holder.participantsText.setText(String.valueOf(reservation.getNumParticipants()));
        holder.notesText.setText(reservation.getNotes());

        // Set the delete button functionality
        holder.deleteButton.setOnClickListener(v -> {
            // Call the deleteClickListener to handle the delete action
            deleteClickListener.onDeleteClick(reservation, position);
        });
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    // ViewHolder class now includes the delete button
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateText, timeText, participantsText, notesText;
        private final Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.text_reservation_date);
            timeText = itemView.findViewById(R.id.text_reservation_time);
            participantsText = itemView.findViewById(R.id.text_reservation_participants);
            notesText = itemView.findViewById(R.id.text_reservation_notes);
            deleteButton = itemView.findViewById(R.id.delete_button);  // Initialize delete button
        }
    }

    // Interface to handle delete button click
    public interface OnDeleteClickListener {
        void onDeleteClick(Reservation reservation, int position);
    }
}
