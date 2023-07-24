package by.shvants.avtomobilka.data.repository

import by.shvants.avtomobilka.data.mapper.CarToDomainMapper
import by.shvants.avtomobilka.data.CarApi
import by.shvants.avtomobilka.data.mapper.PostsToDomainMapper
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.RequestResult
import by.shvants.avtomobilka.utils.toListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val carApi: CarApi,
    private val postsToDomainMapper: PostsToDomainMapper,
) : PostRepository {

    private var postsPage = 1
    private var postsCache = mutableListOf<Post>()

    override suspend fun fetchPosts(id: Int): RequestResult<Posts> {
        return withContext(Dispatchers.IO) {
            try {
                val postsResponse = carApi.getPosts(id, postsPage++)
                val posts = postsToDomainMapper.map(postsResponse)

                postsCache.addAll(posts.posts)

                RequestResult.Success(posts)
            } catch (e: Exception) {
                RequestResult.Error(e)
            }
        }
    }

    override suspend fun getPosts(id: Int): RequestResult<List<Post>> {
        return withContext(Dispatchers.IO) {
            try {
                val postsResponse = carApi.getPosts(id, postsPage++)
                val posts = postsToDomainMapper.map(postsResponse)

                postsCache.addAll(posts.posts)

                RequestResult.Success(postsCache)
            } catch (e: Exception) {
                RequestResult.Error(e)
            }
        }
    }
}