package com.example.todolist.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.todolist.R
import com.example.todolist.adapter.TodoRecyclerViewAdapter
import com.example.todolist.data.TodoEntity
import com.example.todolist.databinding.ActivityTodoListBinding
import com.example.todolist.fragment.dialog.AddTodoDialogFragment
import com.example.todolist.fragment.dialog.EditTodoDialogFragment
import com.example.todolist.viewmodel.TodoViewModel

class TodoListActivity : AppCompatActivity() {
    private lateinit var activityTodoListBinding:ActivityTodoListBinding
    private lateinit var todoRecyclerViewAdapter: TodoRecyclerViewAdapter
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(this)[TodoViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityTodoListBinding = ActivityTodoListBinding.inflate(layoutInflater)

        todoRecyclerViewAdapter = TodoRecyclerViewAdapter()

        todoViewModel.allTodos.observe(this) {
            todoRecyclerViewAdapter.submitList(it)
        }

        activityTodoListBinding.todoList.layoutManager = LinearLayoutManager(this)
        activityTodoListBinding.todoList.adapter = todoRecyclerViewAdapter

        activityTodoListBinding.addTodoButton.setOnClickListener {
            AddTodoDialogFragment(todoViewModel = todoViewModel).show(supportFragmentManager,null)
        }

        // Handle item done button click event.
        todoRecyclerViewAdapter.addOnItemChildClickListener(R.id.todo_item_done_button){ baseQuickAdapter: BaseQuickAdapter<TodoEntity, *>, view: View, i: Int ->
            baseQuickAdapter.getItem(i)
                ?.let {
                    todoViewModel.deleteTodo(todoEntity = it)
                    Toast
                        .makeText(
                            this,
                            it.todoTitle + " has been completed.",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
        }

        // Handle item title button click event.
        todoRecyclerViewAdapter.addOnItemChildClickListener(R.id.todo_item_title){ baseQuickAdapter: BaseQuickAdapter<TodoEntity, *>, view: View, i: Int ->
            baseQuickAdapter.getItem(i)
                ?.let {

                    EditTodoDialogFragment(
                        todoViewModel = todoViewModel,
                        todoId = it.todoId,
                        editText = it.todoTitle
                    )
                        .show(
                            supportFragmentManager,
                            null
                        )
                }
        }

        val view = activityTodoListBinding.root
        setContentView(view)
    }
}