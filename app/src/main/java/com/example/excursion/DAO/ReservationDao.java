package com.example.excursion.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.excursion.entities.Reservation;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    void insert(Reservation reservation);

    @Update
    void update(Reservation reservation);

    @Delete
    void delete(Reservation reservation);

    @Query("SELECT * FROM `reservation-table`")
    List<Reservation> getAllReservations();

    // Query to delete all reservations (optional, in case you need to clear all data)
    @Query("DELETE FROM `reservation-table`")
    void deleteAllReservations();
}
