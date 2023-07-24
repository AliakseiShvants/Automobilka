package by.shvants.avtomobilka.data.model

data class PostsResponse(
    val posts: List<PostResponse>?,
    val user: UserResponse?,
)