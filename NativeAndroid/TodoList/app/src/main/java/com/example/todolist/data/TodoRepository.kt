package com.example.todolist.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

private const val TODO_DATABASE_NAME = "todos"

class TodoRepository private constructor(context: Context){
    private val todoDatabase : TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        TODO_DATABASE_NAME
    ).build()

    private val todoDao = todoDatabase.todoDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllTodos():Flow<List<TodoEntity>> = todoDao.getAllTodos()

    fun insertTodo(todoEntity: TodoEntity) {
        executor.execute { todoDao.insertTodo(todoEntity = todoEntity) }
    }

    fun deleteTodo(todoEntity: TodoEntity) {
        executor.execute{ todoDao.deleteTodo(todoEntity = todoEntity) }
    }

    fun updateTodo(todoEntity: TodoEntity) {
        executor.execute{ todoDao.updateTodo(todoEntity) }
    }

    companion object{
        private var INSTANCE: TodoRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null){
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository {
            return INSTANCE ?: throw IllegalStateException("TodoRepository must be initialized")
        }
    }
}