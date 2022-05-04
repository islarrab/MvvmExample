package com.example.encoratask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encoratask.data.entity.Character
import com.example.encoratask.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    // TODO: this should be resolved using dependency injection
    private val repository = CharacterRepository()

    private val _characterLiveData = MutableLiveData<Character>()
    val characterLiveData: LiveData<Character> = _characterLiveData

    fun refreshCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacter(id)

            _characterLiveData.postValue(response!!)
        }
    }
}