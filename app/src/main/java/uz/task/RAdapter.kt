package uz.task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_modelone.view.*
import uz.task.models.CommonModel
import uz.task.models.ModelOne
import uz.task.models.ModelTwo
import java.util.*


class RAdapter : RecyclerView.Adapter<RAdapter.ViewHolder>() {

    var items = arrayListOf<CommonModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<CommonModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                if (viewType == 1) R.layout.layout_modelone else
                    R.layout.layout_modeltwo, parent, false
            )
        )
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onBindViewHolder(holder: RAdapter.ViewHolder, position: Int) {
        when (items[position]) {
            is ModelOne -> holder.bindOne(items[position] as ModelOne)
            is ModelTwo -> holder.bindTwo(items[position] as ModelTwo)
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].getType()

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindOne(item: ModelOne) {
            itemView.text.text = item.id.toString()
        }

        fun bindTwo(item: ModelTwo) {
            itemView.text.text = item.name
        }
    }
}