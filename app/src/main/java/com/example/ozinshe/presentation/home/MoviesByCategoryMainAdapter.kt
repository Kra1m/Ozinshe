package com.example.ozinshe.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.MainMoviesResponseItem
import com.example.ozinshe.data.model.MoviesByCategoryMainModel
import com.example.ozinshe.data.model.MoviesByCategoryMainModelItem
import com.example.ozinshe.data.model.Movy
import com.example.ozinshe.databinding.ItemCategoryMovieBinding
import com.example.ozinshe.databinding.ItemMainMoviesBinding

class MoviesByCategoryMainAdapter: RecyclerView.Adapter<MoviesByCategoryMainAdapter.MoviesByCategoryMainHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Movy>(){
        override fun areItemsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

    }

    private var listenerClickAtItem : RcViewMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewMainMoviesCallback){
        this.listenerClickAtItem = listener
    }


    inner class MoviesByCategoryMainHolder(private var binding: ItemCategoryMovieBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bindItem(movieItem: Movy){
            Glide.with(itemView.context)
                .load(movieItem.poster.link)
                .into(binding.imgCategoryMovie)


            binding.textMovieByCategoryTittle.text = movieItem.name
            binding.textMovieByCategoryDescription.text = movieItem.categories[0].name
            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(movieItem.id)
            }
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    fun submitList(listMoviesMain: List<Movy>){
        differ.submitList(listMoviesMain)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MoviesByCategoryMainHolder {
        return MoviesByCategoryMainHolder(
            ItemCategoryMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MoviesByCategoryMainHolder,
        position: Int
    ) {
        holder.bindItem(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}