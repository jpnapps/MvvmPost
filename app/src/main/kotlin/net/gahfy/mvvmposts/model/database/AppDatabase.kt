package net.gahfy.mvvmposts.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.gahfy.mvvmposts.model.Photo
import net.gahfy.mvvmposts.model.PhotoDao
import net.gahfy.mvvmposts.model.Post
import net.gahfy.mvvmposts.model.PostDao

@Database(entities = [Post::class, Photo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun photoDao(): PhotoDao
}