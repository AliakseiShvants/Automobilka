package by.shvants.avtomobilka.data.model

data class PostResponse(
    val author: UserResponse?,
    val comment_count: Int?,
    val created_at: String?,
    val id: Int?,
    val img: String?,
    val like_count: Int?,
    val text: String?,
)