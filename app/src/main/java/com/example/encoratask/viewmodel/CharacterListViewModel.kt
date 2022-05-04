package com.example.encoratask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encoratask.data.entity.Character
import com.example.encoratask.data.entity.Info
import com.example.encoratask.data.repository.CharacterRepository
import kotlinx.coroutines.launch

// TODO: checkout https://developer.android.com/topic/libraries/architecture/paging/v3-overview for better paging support

class CharacterListViewModel : ViewModel() {

    // TODO: this should be resolved using dependency injection
    private val repository = CharacterRepository()

    private var currentPage = 0;
    private var info: Info? = null

    private val _characterListLiveData = MutableLiveData<List<Character>>()
    val characterListLiveData: LiveData<List<Character>> = _characterListLiveData

    fun loadNextPage() {
        if (info == null || currentPage < info!!.pages)
            currentPage++
        viewModelScope.launch {

            val response = repository.getCharacters(currentPage)

            info = response?.info

            val previousList = _characterListLiveData.value ?: emptyList()
            val results = (response?.results ?: emptyList())
            _characterListLiveData.postValue(previousList + results)
        }
    }
}