package net.gahfy.mvvmposts.network

import io.reactivex.Observable
import net.gahfy.mvvmposts.model.Photo

import retrofit2.http.GET

interface PhotoApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>
}