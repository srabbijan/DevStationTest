package com.example.devstationtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devstationtest.api.Resource
import com.example.devstationtest.model.Users
import com.example.devstationtest.repository.UserListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repo: UserListRepo) : ViewModel(){
    private val _getUsers = MutableLiveData<Resource<Users>>()
    val getUsers: LiveData<Resource<Users>> get() = _getUsers

    fun getUsers(){
        viewModelScope.launch {
            _getUsers.postValue(Resource.loading(null))
            try {
                val res = repo.getUsers()
                if (res!=null){
                    _getUsers.postValue(Resource.success(data = res))
                }
                else{
                    _getUsers.postValue(Resource.error(null, message = "Error Occurred!"))
                }
            }
            catch (e:java.lang.Exception){
                _getUsers.postValue(Resource.error(null, message = e.message?:"Error Occurred!"))
            }
        }
    }
}