package com.sample.cats.presentation.features

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.cats.domain.GetCatBreedsUseCase
import com.sample.cats.domain.SetFavoriteStatusUseCase
import com.sample.cats.domain.base.Resource
import com.sample.cats.domain.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedVM @Inject
constructor(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val setFavoriteStatusUseCase: SetFavoriteStatusUseCase
) : ViewModel() {

    private val _catListRes = mutableStateOf<Resource<List<CatBreed>>?>(Resource.Loading)
    val catListRes: State<Resource<List<CatBreed>>?> get() = _catListRes

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch {
            when (val resource = getCatBreedsUseCase(Unit)) {
                is Resource.Loading -> {
                    _catListRes.value = Resource.Loading
                }

                is Resource.Success -> {
                    resource.data.collect { catList ->
                        _catListRes.value = Resource.Success(catList)
                    }
                }

                is Resource.Error -> {
                    _catListRes.value = Resource.Error(resource.errorModel)
                }
            }
        }
    }

    fun getCatBreedById(id: String): CatBreed? {
        val catList = (_catListRes.value as? Resource.Success)?.data
        return catList?.find { it.id == id }
    }

    fun setFavoriteStatus(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite)
                setFavoriteStatusUseCase(Pair(id, false))
            else
                setFavoriteStatusUseCase(Pair(id, true))
        }
    }
}
