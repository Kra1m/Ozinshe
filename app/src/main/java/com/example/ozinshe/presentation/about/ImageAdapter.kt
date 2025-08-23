package com.example.ozinshe.presentation.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.data.model.Screenshot
import com.example.ozinshe.databinding.ItemImageScreenshotsBinding

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Screenshot>(){
        override fun areItemsTheSame(
            oldItem: Screenshot,
            newItem: Screenshot
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Screenshot,
            newItem: Screenshot
        ): Boolean {
            return oldItem == newItem
        }

    }

    private var listenerClickAtItem : RcViewImageCallback? = null

    fun setOnImageClickListener(listener: RcViewImageCallback){
        this.listenerClickAtItem = listener
    }


    inner class ImageHolder(private var binding: ItemImageScreenshotsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bindItem(image: Screenshot){
            Glide.with(itemView.context)
                .load(image.link)
                .into(binding.imageView)

            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(image.link)
            }
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    fun submitList(list: List<Screenshot>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            ItemImageScreenshotsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder( holder: ImageHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}