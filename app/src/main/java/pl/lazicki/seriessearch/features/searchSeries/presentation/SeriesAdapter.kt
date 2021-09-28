package pl.lazicki.seriessearch.features.searchSeries.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.lazicki.seriessearch.R
import pl.lazicki.seriessearch.core.extensions.inflate
import pl.lazicki.seriessearch.core.extensions.loadImage
import pl.lazicki.seriessearch.databinding.ItemSerieBinding
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SerieViewHolder>() {

    private val series = mutableListOf<SerieDisplayable>()

    fun setItems(items: List<SerieDisplayable>) {
        series.clear()
        series.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        return SerieViewHolder(
            parent.inflate(R.layout.item_serie)
        )
    }

    override fun getItemCount() = series.size

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val episode = series[position]
        holder.bind(episode)
    }

    class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSerieBinding.bind(itemView)

        fun bind(serie: SerieDisplayable) {
            with(binding) {
                textViewName.text = serie.name
                textViewGenres.text = serie.genres.joinToString()
                serie.image?.let { image -> imageViewSerie.loadImage(image) }
            }
        }
    }
}