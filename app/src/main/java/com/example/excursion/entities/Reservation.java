package com.example.excursion.entities;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "reservation-table")
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String time;
    private int numParticipants;
    private String notes;

    public Reservation() {
    }

@Ignore
public Reservation(String date, String time, int numParticipants, String notes) {
        this.date = date;
        this.time = time;
        this.numParticipants = numParticipants;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getNumParticipants() { return numParticipants; }
    public void setNumParticipants(int numParticipants) { this.numParticipants = numParticipants; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
