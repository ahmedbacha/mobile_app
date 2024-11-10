package com.example.excursion.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excursion.Adapters.ReservationsAdapter;
import com.example.excursion.DAO.AppDatabase;
import com.example.excursion.R;
import com.example.excursion.entities.Reservation;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewReservationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationsAdapter adapter;
    private final Executor databaseExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        recyclerView = findViewById(R.id.recycler_view_reservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load reservations from database
        databaseExecutor.execute(() -> {
            List<Reservation> reservations = AppDatabase.getInstance(getApplicationContext()).reservationDao().getAllReservations();
            runOnUiThread(() -> {
                // Pass the delete listener to the adapter
                adapter = new ReservationsAdapter(reservations, new ReservationsAdapter.OnDeleteClickListener() {
                    @Override
                    public void onDeleteClick(Reservation reservation, int position) {
                        // Delete reservation from the database in a background thread
                        databaseExecutor.execute(() -> {
                            AppDatabase.getInstance(getApplicationContext()).reservationDao().delete(reservation);
                            // Remove reservation from the list and update the RecyclerView
                            reservations.remove(position);
                            runOnUiThread(() -> {
                                adapter.notifyItemRemoved(position);
                                adapter.notifyItemRangeChanged(position, reservations.size());
                            });
                        });
                    }
                });
                recyclerView.setAdapter(adapter);
            });
        });
    }
}
