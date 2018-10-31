package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo {

    val albumId: Int,
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
}