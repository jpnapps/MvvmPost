package net.gahfy.mvvmposts.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ItemPhotoBinding
import net.gahfy.mvvmposts.model.Photo

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    private lateinit var photoList:List<Photo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListAdapter.ViewHolder {
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoListAdapter.ViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return if(::photoList.isInitialized) photoList.size else 0
    }

    fun updatePhotoList(photoList:List<Photo>){
        this.photoList = photoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = PhotoViewModel()

        fun bind(photo: Photo){
            viewModel.bind(photo)
            binding.viewModel = viewModel
        }
    }
}