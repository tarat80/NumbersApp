package com.example.numbersapp.presentation_app.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.domain.model.Resource
import com.example.numbersapp.presentation_app.dispatcherprovider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ScreenSearchVM @Inject constructor(
    private val repository: Repository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private var joBa: Job? = null

    private val _state = MutableStateFlow(StateSSearch())
    val state = _state.asStateFlow()

  /*  init{
        getByNumber()
    }*/

    fun searchChanged(string: String) {
        val possible = setOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var temp = ""

        string.map { symbol-> if (possible.contains(symbol.toString())) temp += symbol }

        _state.value = _state.value.copy(searchString = temp)
    }
    fun getByNumber() {
        joBa?.cancel()
        joBa = viewModelScope.launch(dispatcherProvider.main) {
            repository.getNumberInfo(_state.value.searchString)
                          .flowOn(dispatcherProvider.io).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { _state.value = _state.value.copy(numbersAndMeans = it) }
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(errorMessage = result.message)
                    }
                }
            }.launchIn(this)
        }
    }
    fun errorHaveRead() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}