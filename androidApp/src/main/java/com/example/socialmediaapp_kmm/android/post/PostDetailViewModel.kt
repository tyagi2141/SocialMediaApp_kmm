package com.example.socialmediaapp_kmm.android.post

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dipumba.ytsocialapp.android.common.fake_data.Post
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.example.socialmediaapp_kmm.android.common.fake_data.Comment
import com.example.socialmediaapp_kmm.android.common.fake_data.sampleComments
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostDetailViewModel: ViewModel() {
    var postUiState by mutableStateOf(PostUiState())
        private set

    var commentsUiState by mutableStateOf(CommentsUiState())
        private set


    fun fetchData(postId: String){
        viewModelScope.launch {
            postUiState = postUiState.copy(isLoading = true)
            commentsUiState = commentsUiState.copy(isLoading = true)
            delay(500)

            postUiState = postUiState.copy(
                isLoading = false,
                post = samplePosts.find { it.id == postId }
            )
Log.e("viewModelResult","${postUiState.post}")
            commentsUiState = commentsUiState.copy(
                isLoading = false,
                comments = sampleComments
            )
        }
    }
}

data class PostUiState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val errorMessage: String? = null
)

data class CommentsUiState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = listOf(),
    val errorMessage: String? = null
)

/*
class PostDetailViewModel : ViewModel() {

    var postUiState by mutableStateOf(PostUiState())
        private set

    var commentsUiState by mutableStateOf(CommentsUiState())
        private set

init {
    //fetchData("11")
}


    fun fetchData(PostId: String) {

        viewModelScope.launch {

            postUiState = postUiState.copy(isLoading = true)
            commentsUiState = commentsUiState.copy(isLoading = true)
            delay(500)

            postUiState = postUiState.copy(isLoading = false, post = samplePosts.find { it.id == PostId })

            Log.e("viewModelResult","$PostId ${postUiState.post}")
            commentsUiState = commentsUiState.copy(isLoading = false, comments = sampleComments)
        }
    }

}

data class PostUiState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val errorMessage: String? = null
)

data class CommentsUiState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = listOf(),
    val errorMessage: String? = null
)*/
