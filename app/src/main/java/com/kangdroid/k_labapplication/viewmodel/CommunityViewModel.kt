package com.kangdroid.k_labapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kangdroid.k_labapplication.data.*
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommunityViewModel (application: Application) : AndroidViewModel(application) {

    // Live data
    val liveCommunity: MutableLiveData<Community> = MutableLiveData()
    val liveSimpleClassList: MutableLiveData<List<SimplifiedCommunity>> = MutableLiveData()
    val liveMyPageCommunityList: MutableLiveData<List<SimplifiedMyPageCommunity>> = MutableLiveData()

    val liveSealedUser: MutableLiveData<SealedUser> = MutableLiveData()
    val liveManagerConfirmCommunity: MutableLiveData<ManagerConfirmCommunity> = MutableLiveData()

    val errorData: MutableLiveData<Throwable> = MutableLiveData() //need??

    fun getClassList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.getClassList()
                }.onSuccess {

                    withContext(Dispatchers.Main){
                        liveSimpleClassList.value = it
                    }

                }
            }
        }
    }

    fun getDetailedClass(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.getDetailedClass(id)
                }.onSuccess {
                    withContext(Dispatchers.Main) {
                        liveCommunity.value = it
                    }
                }
            }
        }
    }

    fun registerClass(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.registerClass(id)
                }.onSuccess {
                    withContext(Dispatchers.Main){
                        liveMyPageCommunityList.value = it
                    }
                }
            }
        }
    }

    fun getUser() {
        // SealedUser
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.getUser()
                }.onSuccess {
                    withContext(Dispatchers.Main){
                        liveSealedUser.value = it
                    }
                }
            }
        }
    }

    fun getUserParticipatedClass(isParticipant: Boolean){
        // List<SimplifiedMyPageCommunity>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.getUserParticipatedClass(isParticipant = isParticipant)
                }.onSuccess {
                    withContext(Dispatchers.Main){
                        liveMyPageCommunityList.value = it
                    }
                }
            }
        }
    }

    fun getClassParticipants(id: Long){
        // ManagerConfirmCommunity
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    ServerRepositoryImpl.getClassParticipants(id = id)
                }.onSuccess {
                    withContext(Dispatchers.Main){
                        liveManagerConfirmCommunity.value = it
                    }
                }
            }
        }
    }
}