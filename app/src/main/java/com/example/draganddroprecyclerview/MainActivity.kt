package com.example.draganddroprecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.draganddroprecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        prepareData()

        val adapter = RecyclerAdapter(itemList)
        binding.recyclerView.adapter = adapter

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun prepareData() {
        itemList.add("Item 1")
        itemList.add("Item 2")
        itemList.add("Item 3")
        itemList.add("Item 4")
        itemList.add("Item 5")
        itemList.add("Item 6")
        itemList.add("Item 7")
    }


    private val itemTouchHelper by lazy {

        val simpleItemTouchCallBack =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    val adapter = recyclerView.adapter as RecyclerAdapter
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition

                    adapter.notifyItemMoved(from, to)

                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                }

                override fun onSelectedChanged(
                    viewHolder: RecyclerView.ViewHolder?,
                    actionState: Int
                ) {
                    super.onSelectedChanged(viewHolder, actionState)

                    if(actionState == ACTION_STATE_DRAG){
                        viewHolder?.itemView?.alpha = 0.8f
                    }
                }

                override fun clearView(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) {
                    super.clearView(recyclerView, viewHolder)

                    viewHolder?.itemView?.alpha = 1f
                }
            }

        ItemTouchHelper(simpleItemTouchCallBack)
    }
}