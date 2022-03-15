package uz.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import uz.task.models.CommonModel
import uz.task.models.ModelOne
import uz.task.models.ModelTwo

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {

        adapter = RAdapter()

        val items = arrayListOf<CommonModel>()
        for (i in 0..20) {
            items.addAll(arrayOf(ModelOne(i), ModelTwo("txt$i")))
        }

        recycler.adapter = adapter.apply {
            setData(items)
        }

        ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler)

    }

    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP.or(ItemTouchHelper.DOWN).or(ItemTouchHelper.END)
            .or(ItemTouchHelper.START), 0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val start = viewHolder.adapterPosition
            val end = target.adapterPosition

            adapter.onItemMove(start, end)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }
    }
}