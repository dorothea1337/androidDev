package ru.mirea.ivanova.nasareport.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.domain.models.Asteroid

class AsteroidAdapter(
    private var list: List<Asteroid>
) : RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {

    fun setData(newList: List<Asteroid>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asteroid_row, parent, false)
        return AsteroidViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class AsteroidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvDiameter: TextView = itemView.findViewById(R.id.tvDiameter)
        private val tvDanger: TextView = itemView.findViewById(R.id.tvDanger)
        private val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        private val tvSpeed: TextView = itemView.findViewById(R.id.tvSpeed)

        fun bind(asteroid: Asteroid) {
            tvName.text = asteroid.name
            tvDiameter.text = "%.2f km".format(asteroid.diameter)
            tvDanger.text = if (asteroid.isDangerous) "Dangerous" else "Safe"
            tvDistance.text = "%.0f km".format(asteroid.distance)
            tvSpeed.text = "%.0f km/h".format(asteroid.speed)
        }
    }
}