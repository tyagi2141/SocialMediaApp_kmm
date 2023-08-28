package com.example.socialmediaapp_kmm.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dipumba.ytsocialapp.android.common.fake_data.Post
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.dipumba.ytsocialapp.android.common.fake_data.sampleUsers
import com.example.socialmediaapp_kmm.android.home.onBoarding.OnBoardIngUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var postUiState by mutableStateOf(PostUiState())
        private set

    var onBoardingUiState by mutableStateOf(OnBoardIngUiState())
        private set


    init {
        fetchData()
    }

    fun fetchData() {
        postUiState = postUiState.copy(isLoading = true)
        onBoardingUiState = onBoardingUiState.copy(isLoading = true)

        viewModelScope.launch {
            delay(1000)

            postUiState = postUiState.copy(isLoading = false, posts = samplePosts)
            onBoardingUiState = onBoardingUiState.copy(
                isLoading = false,
                users = sampleUsers,
                shouldShowOnBoarding = true
            )
        }
    }

}

data class PostUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val errorMessage: String? = null
)