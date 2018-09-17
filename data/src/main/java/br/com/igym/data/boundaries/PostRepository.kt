package br.com.igym.data.boundaries

import br.com.igym.domain.entities.Post
import io.reactivex.Single

interface PostRepository {
    fun getPosts(id: Int): Single<Post>
}