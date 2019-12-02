package com.arctouch.codechallenge.ui.upcomingMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.ListItemShortMovieBinding
import com.example.domain.model.ShortMovieData

class UpcomingMoviesAdapter(
        private val viewModel: UpcomingMoviesViewModel
) : PagedListAdapter<ShortMovieData, UpcomingMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_short_movie,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.item = getItem(position)
        binding?.viewModel = viewModel
        binding?.executePendingBindings()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemShortMovieBinding? = DataBindingUtil.bind(itemView)
    }

    companion object {
        // Check if the items in list are different and reload them
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShortMovieData>() {

            override fun areItemsTheSame(oldItem: ShortMovieData, newItem: ShortMovieData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShortMovieData, newItem: ShortMovieData): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}