package com.example.encoratask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encoratask.data.entity.Character
import com.example.encoratask.data.entity.Info
import com.example.encoratask.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    // TODO: this should be resolved using dependency injection
    private val repository = CharacterRepository()

    private var info: Info? = null

    private val _characterListLiveData = MutableLiveData<List<Character>>()
    val characterListLiveData: LiveData<List<Character>> = _characterListLiveData

    fun refreshCharacters(page: Int?) {
        viewModelScope.launch {
            val response = repository.getCharacters(page)

            info = response?.info

            _characterListLiveData.postValue(response!!.results)
        }
    }
}