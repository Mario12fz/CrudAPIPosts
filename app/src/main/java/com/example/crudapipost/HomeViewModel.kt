package com.example.crudapipost

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crudapipost.models.HomeRepository
import com.example.crudapipost.models.Posts

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private var homeRepository: HomeRepository? = null
    var postModelListData: LiveData<List<Posts>>? = null
    var createPostsLiveData: LiveData<Posts>? = null
    var deletePostsLiveData: LiveData<Boolean>? = null

    init {
        homeRepository = HomeRepository()
        postModelListData = MutableLiveData()
        createPostsLiveData = MutableLiveData()
        deletePostsLiveData = MutableLiveData()
    }

    fun getAllPosts(){
        postModelListData = homeRepository?.fetchAllPosts()
    }

    fun  createPosts(posts: Posts){
        createPostsLiveData = homeRepository?.createPosts(posts)
    }

    fun deletePosts(id: Int){
        deletePostsLiveData = homeRepository?.deletePosts(id)
    }


}