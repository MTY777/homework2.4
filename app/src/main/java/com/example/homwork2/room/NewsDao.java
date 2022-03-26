package com.example.homwork2.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.homwork2.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Query("SELECT * FROM news WHERE title = :s")
    List<News> getItemTitle(String s);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News news);

    @Query("SELECT * FROM news order by title desc")
    List<News> sortAll();

    @Delete
    void deleteTask(News news);

    @Query("SELECT * FROM news order by title Desc")
    List<News> sort();
}
