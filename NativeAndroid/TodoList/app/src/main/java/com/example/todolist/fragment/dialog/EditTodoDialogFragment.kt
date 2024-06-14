package com.example.todolist.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todolist.data.TodoEntity
import com.example.todolist.databinding.EditTodoDialogFragmentBinding
import com.example.todolist.viewmodel.TodoViewModel

class EditTodoDialogFragment(
    private val todoViewModel: TodoViewModel,
    private val todoId: String,
    private val editText: String
) : DialogFragment(){
    private var _editTodoDialogBinding:EditTodoDialogFragmentBinding? = null
    private val editTodoDialogBinding get() = _editTodoDialogBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _editTodoDialogBinding = EditTodoDialogFragmentBinding.inflate(inflater,container,false)

        editTodoDialogBinding.editTextView.setText(editText.toCharArray(), 0, editText.length)

        return editTodoDialogBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _editTodoDialogBinding = null
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Handle cancel button click event.
        editTodoDialogBinding.cancelButton.setOnClickListener {
            // Close the dialog
            dismiss()
        }

        // Handle save button click event.
        editTodoDialogBinding.saveButton.setOnClickListener {

            if (editTodoDialogBinding.editTextView.text.isNotBlank()){
                val todoTitle = editTodoDialogBinding.editTextView.text
                todoViewModel.updateTodo(TodoEntity(todoId = todoId, todoTitle = todoTitle.toString()))
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