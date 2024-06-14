package com.example.todolist.application

import android.app.Application
import com.example.todolist.data.TodoDatabase
import com.example.todolist.data.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodoListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TodoRepository.initialize(this)
    }
}