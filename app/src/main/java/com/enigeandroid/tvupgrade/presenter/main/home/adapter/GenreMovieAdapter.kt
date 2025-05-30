package com.enigeandroid.tvupgrade.presenter.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enigeandroid.tvupgrade.databinding.GenreItemBinding
import com.enigeandroid.tvupgrade.presenter.model.GenrePresentation

class GenreMovieAdapter: ListAdapter<GenrePresentation, GenreMovieAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<GenrePresentation>() {
            override fun areItemsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            GenreItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val genre = getItem(position)

        holder.binding.genriName.text = genre.name

        val moviewAdapter = MovieAdapter(holder.binding.root.context)
        val layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        holder.binding.recyclerMovies.layoutManager = layoutManager
        holder.binding.recyclerMovies.setHasFixedSize(true)
        holder.binding.recyclerMovies.adapter = moviewAdapter
        moviewAdapter.submitList(genre.movies)
    }

    inner class MyViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}