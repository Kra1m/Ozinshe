package com.example.ozinshe.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.Screenshot
import com.example.ozinshe.data.model.Video
import com.example.ozinshe.data.model.VideoResponse
import com.example.ozinshe.databinding.FragmentSeriesBinding
import com.example.ozinshe.databinding.ItemImageScreenshotsBinding
import com.example.ozinshe.databinding.ItemSeriesBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Video>(){
        override fun areItemsTheSame(
        oldItem: Video,
        newItem: Video
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Video,
            newItem: Video
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    fun submitList(list: List<Video>){
        differ.submitList(list)
    }

    private var listenerClickAtItem : RcViewItemClickVideoCallBack? = null

    fun setOnVideoClickListener(listener: RcViewItemClickVideoCallBack){
        this.listenerClickAtItem = listener
    }

    inner class SeriesHolder(private var binding: ItemSeriesBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(video: Video) {
            binding.apply {
                Glide.with(itemView)
                    .load("http://img.youtube.com/vi/${video.link}/maxresdefault.jpg")
                    .into(binding.imgTvSeries)
                textTvSeriesNumbers.text = "${video.number}-ші бөлім"
                root.setOnClickListener {
                    listenerClickAtItem?.onVideoItemClick(video.link)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesAdapter.SeriesHolder {
        return SeriesHolder(
            ItemSeriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: SeriesAdapter.SeriesHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}