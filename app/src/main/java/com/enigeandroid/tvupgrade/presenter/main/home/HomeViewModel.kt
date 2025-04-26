package com.enigeandroid.tvupgrade.presenter.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.enigeandroid.tvupgrade.data.mapper.toPresentation
import com.enigeandroid.tvupgrade.domain.usecase.movie.GetGenresUseCase
import com.enigeandroid.tvupgrade.domain.usecase.movie.GetMoviesByGenreUseCase
import com.enigeandroid.tvupgrade.util.Constantes.Movie
import com.enigeandroid.tvupgrade.util.StateView
import com.enigeandroid.tvupgrade.util.chaveAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
): ViewModel() {

    fun getGenres() = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())
            val genres = getGenresUseCase.invoke(
                apiKey = chaveAPI,
                language = Movie.LANGUAGE
            ).map { it.toPresentation() }

            emit(StateView.Success(genres))

        }catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())
            val movies = getMoviesByGenreUseCase.invoke(
                apiKey = chaveAPI,
                language = Movie.LANGUAGE,
                genreId = genreId
            )

            emit(StateView.Success(movies))

        }catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

}