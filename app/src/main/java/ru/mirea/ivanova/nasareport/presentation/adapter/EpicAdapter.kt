package ru.mirea.ivanova.nasareport.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.domain.models.EpicImage

class EpicAdapter : RecyclerView.Adapter<EpicAdapter.EpicViewHolder>() {

    private val items = mutableListOf<EpicImage>()

    fun setItems(newItems: List<EpicImage>) {
        // Simple replace. For better performance, use DiffUtil (example below)
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
            titleTv.text = item.caption
            // Загрузка картинки: здесь можно подключить Coil/Glide/Picasso. Сейчас — placeholder.
            // Если добавишь Coil: Coil.imageLoader / imageView.load(item.imageUrl)
            imageIv.contentDescription = item.caption
            // TODO: подключить загрузчик картинок и раскомментировать
            // imageView.load(item.imageUrl)
        }
    }
}
