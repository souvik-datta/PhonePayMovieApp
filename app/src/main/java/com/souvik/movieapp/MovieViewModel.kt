package com.souvik.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private var _movieDataList : MutableLiveData<List<Movie>> = MutableLiveData()
    val movieDataList: LiveData<List<Movie>>
        get() {
           return _movieDataList
        }

    fun fetchData(pageNo:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val result = ApiClient.apiInterface?.getMovieData("38a73d59546aa378980a88b645f487fc","en-US", pageNo)
            if(result?.isSuccessful == true && result.body()?.results != null){
                _movieDataList.postValue(result.body()?.results)
            }else{
                _movieDataList.postValue(ArrayList<Movie>())
            }
        }
    }
}