package uz.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {

        adapter = RAdapter()

        val items = arrayListOf<Int>()
        for (i in 0..100)
            items.add(i)

        recycler.adapter = adapter.apply {
            setData(items)
        }

        ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler)

    }

    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP.or(ItemTouchHelper.DOWN).or(ItemTouchHelper.END)
            .or(ItemTouchHelper.START),0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val start = viewHolder.adapterPosition
            val end = target.adapterPosition

            Collections.swap(adapter.items, start, end)
            recyclerView.adapter?.notifyItemMoved(start,end)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }
    }
}