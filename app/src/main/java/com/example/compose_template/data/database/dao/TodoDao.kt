package com.example.compose_template.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.compose_template.data.models.todo.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * from todo")
    fun flowAll(): Flow<List<TodoData>>

    @Query("SELECT * from todo")
    suspend fun getAll(): List<TodoData>

    @Query("SELECT * from todo WHERE id =:id")
    suspend fun getById(id: Int): TodoData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNew(todoData: TodoData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(todoData: TodoData)

    @Query("UPDATE todo SET isFavorite=:isFavorite WHERE id = :id")
    suspend fun updateFavoriteById(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM todo WHERE id = :totoId")
    suspend fun deleteById(totoId: Int)

}
