package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PhotoDao {
    @get:Query("SELECT * FROM photo")
    val all: List<Photo>

    @Insert
    fun insertAll(vararg users: Photo)
}