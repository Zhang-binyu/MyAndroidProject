package com.example.todolist.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.example.todolist.R
import com.example.todolist.data.TodoEntity

class TodoRecyclerViewAdapter(itemList: List<TodoEntity> = emptyList())
    : BaseQuickAdapter<TodoEntity, QuickViewHolder>(itemList){
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: TodoEntity?) {
        holder.getView<LinearLayout>(R.id.todo_item)
        holder.setText(R.id.todo_item_title, item?.todoTitle)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.todo_item,parent)
    }
}