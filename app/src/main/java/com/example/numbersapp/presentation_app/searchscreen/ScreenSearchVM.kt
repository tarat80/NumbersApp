package com.example.numbersapp.presentation_app.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.domain.model.Resource
import com.example.numbersapp.presentation_app.dispatcherlist.DispatcherList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ScreenSearchVM @Inject constructor(
    private val repository: Repository,
    private val dispatcherList: DispatcherList
) : ViewModel() {

    private var joBa: Job? = null

    private val _state = MutableStateFlow(StateSSearch())
    val state = _state.asStateFlow()

    fun searchChanged(string: String) {
        val possible = setOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var temp = ""

        string.map { symbol-> if (possible.contains(symbol.toString())) temp += symbol }

        _state.update { it.copy(searchString = temp)}
    }
    fun getByNumber() {
        joBa?.cancel()
        joBa = viewModelScope.launch(dispatcherList.main()) {
            repository.getNumberInfo(_state.value.searchString)
                .flowOn(dispatcherList.io()).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                _state.value = _state.value.copy(numbersAndMeans = it)
                            }
                        }
                        is Resource.Loading -> {
                            _state.update { it.copy(isLoading = result.isLoading) }
                        }
                        is Resource.Error -> {
                            _state.update { it.copy(errorMessage = result.message) }
                        }
                    }
                }.launchIn(this)
        }
    }
    fun errorHaveRead() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}