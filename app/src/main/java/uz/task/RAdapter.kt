package uz.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class RAdapter : RecyclerView.Adapter<RAdapter.ViewHolder>() {

    var items = arrayListOf<Int>()

    fun setData(data: ArrayList<Int>) {
        items.clear()
        items.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RAdapter.ViewHolder, position: Int) =
        holder.bindData(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Int) {
            itemView.text.text = item.toString()
        }
    }
}