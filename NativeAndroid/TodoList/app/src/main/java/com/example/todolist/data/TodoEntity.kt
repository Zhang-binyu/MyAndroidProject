package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey(autoGenerate = false)
    val todoId:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title")
    val todoTitle:String,
    @ColumnInfo(name = "deadline")
    val deadLine:String = ""
)
