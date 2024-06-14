package com.example.todolist.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todolist.data.TodoEntity
import com.example.todolist.databinding.AddTodoDialogFragmentBinding
import com.example.todolist.viewmodel.TodoViewModel

class AddTodoDialogFragment(private val todoViewModel: TodoViewModel) : DialogFragment() {
    private var _addTodoDialogBinding:AddTodoDialogFragmentBinding? = null
    private val addTodoDialogBinding get() = _addTodoDialogBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _addTodoDialogBinding = AddTodoDialogFragmentBinding.inflate(inflater, container, false)

        return addTodoDialogBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Clear cache.
        _addTodoDialogBinding = null
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Handle cancel button click event.
        addTodoDialogBinding.cancelButton.setOnClickListener {
            // Close the dialog
            dismiss()
        }

        // Handle save button click event.
        addTodoDialogBinding.saveButton.setOnClickListener {

            if (addTodoDialogBinding.editTextView.text.isNotBlank()){
                val todoTitle = addTodoDialogBinding.editTextView.text
                todoViewModel.insertTodo(TodoEntity(todoTitle = todoTitle.toString()))
            }else{
                Toast.makeText(
                    context,
                    "Title cannot be empty!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Close the dialog
            dismiss()
        }

        isCancelable = false
    }
}