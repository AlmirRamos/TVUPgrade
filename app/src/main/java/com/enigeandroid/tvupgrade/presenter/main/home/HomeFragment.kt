package com.enigeandroid.tvupgrade.presenter.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.enigeandroid.tvupgrade.databinding.FragmentHomeBinding
import com.enigeandroid.tvupgrade.presenter.main.home.adapter.GenreMovieAdapter
import com.enigeandroid.tvupgrade.presenter.model.GenrePresentation
import com.enigeandroid.tvupgrade.util.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var genreMovieAdapter: GenreMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getGenres()
    }

    private fun getGenres() {
        viewModel.getGenres().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading<*> -> {

                }
                is StateView.Success<*> -> {
                    val genres = stateView.data ?: emptyList()
                    genreMovieAdapter.submitList(genres)
                    getMoviesByGenre(genres)
                }
                is StateView.Error<*> -> {

                }
            }
        }
    }

    private fun getMoviesByGenre(genres: List<GenrePresentation>) {

        val genresMutableList =  genres.toMutableList()

        genresMutableList.forEachIndexed { index, genre ->
            viewModel.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading<*> -> {

                    }
                    is StateView.Success<*> -> {
                        genresMutableList[index] = genre.copy(movies = stateView.data)
                        lifecycleScope.launch {
                            delay(1000)
                            genreMovieAdapter.submitList(genresMutableList)
                        }
                    }
                    is StateView.Error<*> -> {

                    }
                }
            }
        }
    }

    private fun initRecyclerView() {

        genreMovieAdapter = GenreMovieAdapter()

        with(binding.recyclerGenres) {
            setHasFixedSize(true)
            adapter = genreMovieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}