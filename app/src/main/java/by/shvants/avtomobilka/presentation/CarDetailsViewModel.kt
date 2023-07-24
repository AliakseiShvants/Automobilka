package by.shvants.avtomobilka.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.shvants.avtomobilka.base.BaseViewModel
import by.shvants.avtomobilka.data.repository.CarRepositoryImpl
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.RequestResult
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarDetailsViewModel : BaseViewModel(), KoinComponent {

    private val postRepository: PostRepository by inject()

    private val _posts = MutableLiveData<List<Post>?>(null)
    private val _user = MutableLiveData<User?>(null)

    val posts: LiveData<List<Post>?>
        get() = _posts
    val user: LiveData<User?>
        get() = _user

    fun fetchPostsFromRemote(id: Int) {
        viewModelScope.launch {
            when(val result = postRepository.fetchPosts(id)) {
                is RequestResult.Success -> {
                    hideLoading()
                    val posts = result.value as Posts

                    _user.value = posts.user
                    _posts.value = posts.posts
                }
                is RequestResult.Error -> {
                    hideLoading()
                }
            }
        }
    }

    fun getPosts(id: Int) {
        viewModelScope.launch {
            when(val result = postRepository.getPosts(id)) {
                is RequestResult.Success -> {
                    hideLoading()
                    _posts.value = result.value as List<Post>
                }
                is RequestResult.Error -> {
                    hideLoading()
                }
            }
        }
    }
}