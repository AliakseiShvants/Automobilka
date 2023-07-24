package by.shvants.avtomobilka.domain

import by.shvants.avtomobilka.utils.RequestResult

interface PostRepository {
    suspend fun fetchPosts(id: Int): RequestResult<Posts>
    suspend fun getPosts(id: Int): RequestResult<List<Post>>
}