package pl.lazicki.seriessearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.android.ext.android.inject
import pl.lazicki.seriessearch.core.extensions.setVisible
import pl.lazicki.seriessearch.databinding.ActivityMainBinding
import pl.lazicki.seriessearch.features.searchSeries.SearchUiState
import pl.lazicki.seriessearch.features.searchSeries.SeriesDisplayable
import pl.lazicki.seriessearch.features.searchSeries.SeriesViewModel

class MainActivity : AppCompatActivity() {

    private val binding = ActivityMainBinding.inflate(layoutInflater).root
    private val viewModel: SeriesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listenSearchEvent()
        observeSearchResult()
    }

    private fun listenSearchEvent() {
        binding.imageViewSearch.setOnClickListener {
            viewModel.search(binding.editTextSearch.text.toString().trim())
        }

        binding.editTextSearch.doOnTextChanged { text, _, count, _ ->
            if (count >= 3 && text != null)
                viewModel.search(text.toString().trim())
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
        binding.progressBarSearch.setVisible(true)
    }

    private fun showSuccess(series: List<SeriesDisplayable>) {
        binding.progressBarSearch.setVisible(false)
    }

    private fun showError(message: String) {
        binding.progressBarSearch.setVisible(false)
    }
}