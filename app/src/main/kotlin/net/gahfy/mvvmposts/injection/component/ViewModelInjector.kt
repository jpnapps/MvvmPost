package net.gahfy.mvvmposts.injection.component

import dagger.Component
import net.gahfy.mvvmposts.injection.module.NetworkModule
import net.gahfy.mvvmposts.ui.post.PhotoListViewModel
import net.gahfy.mvvmposts.ui.post.PhotoViewModel
import net.gahfy.mvvmposts.ui.post.PostListViewModel
import net.gahfy.mvvmposts.ui.post.PostViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @par
     * am postViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(postViewModel: PostViewModel)



    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(photoListViewModel: PhotoListViewModel)
    /**
     * Injects required dependencies into the specified PhotoViewModel.
     * @par
     * am postViewModel PhotoViewModel in which to inject the dependencies
     */
    fun inject(photoViewModel: PhotoViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}