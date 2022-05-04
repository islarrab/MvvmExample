package com.example.encoratask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encoratask.data.entity.CharacterList
import com.example.encoratask.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    // TODO: this should be resolved using dependency injection
    private val repository = CharacterRepository()

    private val _characterListLiveData = MutableLiveData<CharacterList>()
    val characterListLiveData: LiveData<CharacterList> = _characterListLiveData

    fun refreshCharacter(page: Int?) {
        viewModelScope.launch {
            val response = repository.getCharacters(page)

            _characterListLiveData.postValue(response!!)
        }
    }
}