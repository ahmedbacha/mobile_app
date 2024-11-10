package com.example.excursion.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.excursion.entities.Excursion;

import java.util.List;

@Dao
public interface ExcursionDao {


    @Update
    void update(Excursion excursion);

}
