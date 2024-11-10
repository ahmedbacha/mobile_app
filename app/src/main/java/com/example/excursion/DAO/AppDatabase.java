package com.example.excursion.DAO;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.excursion.entities.Commentaire;
import com.example.excursion.entities.Excursion;
import com.example.excursion.entities.Reservation;

@Database(entities = {Excursion.class, Commentaire.class, Reservation.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ExcursionDao excursionDao();
    public abstract CommentaireDao commentaireDao();
    public abstract ReservationDao reservationDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "gestion_excursions_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
