package by.shvants.avtomobilka.domain

data class Posts(
    val posts: List<Post>,
    val user: User?
)