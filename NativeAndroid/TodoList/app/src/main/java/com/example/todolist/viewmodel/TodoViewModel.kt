package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TodoEntity
import com.example.todolist.data.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel(){
    private val todoRepository = TodoRepository.get()

    val allTodos:LiveData<List<TodoEntity>> = todoRepository.getAllTodos().asLiveData()

    fun insertTodo(todoEntity: TodoEntity){
        todoRepository.insertTodo(todoEntity)
    }

    fun deleteTodo(todoEntity: TodoEntity){
        todoRepository.deleteTodo(todoEntity)
    }

    fun updateTodo(todoEntity: TodoEntity){
        todoRepository.updateTodo(todoEntity)
    }
}