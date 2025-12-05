package ru.mirea.ivanova.nasareport.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.domain.models.EpicImage

class EpicAdapter : RecyclerView.Adapter<EpicAdapter.EpicViewHolder>() {

    private val items = mutableListOf<EpicImage>()

    fun setItems(newItems: List<EpicImage>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpicViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.epic_list_item, parent, false)
        return EpicViewHolder(v)
    }

    override fun onBindViewHolder(holder: EpicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class EpicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTv: TextView = itemView.findViewById(R.id.epicTitle)
        private val imageIv: ImageView = itemView.findViewById(R.id.epicImage)

        fun bind(item: EpicImage) {
            val number = if (item.id.length >= 6) item.id.takeLast(6) else item.id
            titleTv.text = "Earth Picture №${number}"
            imageIv.contentDescription = titleTv.text

            Picasso.get()
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageIv)

            Log.d("EPIC", "Loading: ${item.imageUrl}")
        }
    }
}
