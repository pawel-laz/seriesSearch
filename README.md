# Series Search

Series Search is an Android Application for searching basic information of series.

## Libraries used

* **Koin** for dependency injection, 
* **Retrofit** for api calls, 
* **Glide** for displaying images, 
* **Mockk** for mock test models

### Further development of the application

* Move from LiveData to StateFlow (LiveData will be deprecated but for this moment StateFlow needs Experimental Api Annotate)
```kotlin
//File SeriesViewModel.kt

//from
private val _searchUiState = MutableLiveData<SearchUiState>()
val searchUiState: LiveData<SearchUiState> = _searchUiState

//to
private val _searchUiState = MutableStateFlow(SearchUiState.Success(emptyList()))
val searchUiState: StateFlow<SearchUiState> = _searchUiState
```


* If application gets more features move things related to the search series from MainActivity to a fragment (inside series feature package [pl.lazicki.seriessearch.features.searchSeries.presentation])

* If the application grows, I recommend switching to multi-modularity

Example structure of modules (depending on the demand):

```
app
features
    search (insiede domain module (inside data module))
    nextFeature
    anotherFeature
    ...
utils
    android
    architecture
    extension
    ...
design
    views
    (style.xml, colors.xml and shared drawables)
...
```



####
