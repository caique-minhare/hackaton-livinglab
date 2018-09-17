package br.com.igym.data.repository

import br.com.igym.data.api.ApiClient
import br.com.igym.data.boundaries.PostRepository
import br.com.igym.data.mapper.ApiPostToPostModel

class DefaultPostRepository(val client: ApiClient): PostRepository {
    override fun getPosts(id: Int) = client.getPosts(id).map(ApiPostToPostModel::transform)
}