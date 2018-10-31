package net.gahfy.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Photo

class PhotoViewModel: BaseViewModel() {
    private val photoTitle = MutableLiveData<String>()
    private val photoUrl= MutableLiveData<String>()
    private val photothumbnailUrl = MutableLiveData<String>()

    fun bind(photo: Photo){
        photoTitle.value = photo.title
        photoUrl.value = photo.url
    }

    fun getPhotoTitle(): MutableLiveData<String> {
        return photoTitle
    }

    fun getPhotoUrl(): MutableLiveData<String> {
        return photoUrl
    }
    fun getPhotoThumbnailUrl(): MutableLiveData<String> {
        return photothumbnailUrl
    }
}