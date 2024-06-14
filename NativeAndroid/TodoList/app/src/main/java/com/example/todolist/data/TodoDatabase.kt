package com.example.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao():TodoDao

    companion object{
        @Volatile
        private var INSTANCE:TodoDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ):TodoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todos"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}