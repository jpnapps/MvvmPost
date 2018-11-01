package net.gahfy.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Photo
import net.gahfy.mvvmposts.model.PhotoDao
import net.gahfy.mvvmposts.network.PhotoApi
import javax.inject.Inject

class PhotoListViewModel(private val photoDao:  PhotoDao): BaseViewModel(){
    @Inject
    lateinit var photoApi: PhotoApi
    val photoListAdapter: PhotoListAdapter = PhotoListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPhotos() }

    private lateinit var subscription: Disposable

    init{
        loadPhotos()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPhotos(){
        subscription = Observable.fromCallable { photoDao.all }
                .concatMap {
                    dbPhotoList ->
                    if(dbPhotoList.isEmpty())
                        photoApi.getPhotos().concatMap {
                            apiPhotoList -> photoDao.insertAll(*apiPhotoList.toTypedArray())
                            Observable.just(apiPhotoList)
                        }
                    else
                        Observable.just(dbPhotoList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePhotoListStart() }
                .doOnTerminate { onRetrievePhotoListFinish() }
                .subscribe(
                        { result -> onRetrievePhotoListSuccess(result) },
                        { onRetrievePhotoListError() }
                )
    }

    private fun onRetrievePhotoListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePhotoListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePhotoListSuccess(postList:List<Photo>){
        photoListAdapter.updatePhotoList(postList)
    }

    private fun onRetrievePhotoListError(){
        errorMessage.value = R.string.post_error
    }
}