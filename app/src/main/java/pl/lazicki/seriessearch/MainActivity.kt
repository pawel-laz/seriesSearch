package pl.lazicki.seriessearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.android.ext.android.inject
import pl.lazicki.seriessearch.core.extensions.setVisible
import pl.lazicki.seriessearch.databinding.ActivityMainBinding
import pl.lazicki.seriessearch.features.searchSeries.presentation.SearchUiState
import pl.lazicki.seriessearch.features.searchSeries.presentation.SeriesAdapter
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable
import pl.lazicki.seriessearch.features.searchSeries.presentation.SeriesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SeriesViewModel by inject()
    private val seriesAdapter: SeriesAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        listenSearchEvent()
        observeSearchResult()
    }

    private fun listenSearchEvent() {
        binding.root.imageViewSearch.setOnClickListener {
            viewModel.search(binding.root.editTextSearch.text.toString().trim())
        }

        binding.root.editTextSearch.doOnTextChanged { text, _, count, _ ->
            if (count >= 3 && text != null)
                viewModel.search(text.toString())
            else
                showSuccess(emptyList())
        }
    }

    private fun observeSearchResult() {
        viewModel.searchUiState.observe(this) { result ->
            when (result) {
                is SearchUiState.Loading -> showLoading()
                is SearchUiState.Success -> showSuccess(result.series)
                is SearchUiState.Error -> showError(result.message)
            }
        }
    }

    private fun showLoading() {
        with(binding.root){
            progressBarSearch.setVisible(true)
            textViewError.setVisible(false)
            textViewNoResult.setVisible(false)
        }
    }

    private fun showSuccess(series: List<SerieDisplayable>) {
        with(binding.root) {
            progressBarSearch.setVisible(false)
            textViewError.setVisible(false)
            textViewNoResult.setVisible(series.isEmpty())
        }
        seriesAdapter.setItems(series)
    }

    private fun showError(message: String?) {
        with(binding.root) {
            progressBarSearch.setVisible(false)
            textViewError.apply {
                text = message
                setVisible(true)
            }
        }
    }

    private fun initRecycler() {
        with(binding.root.recyclerViewSeries) {
            adapter = seriesAdapter
        }
    }
}