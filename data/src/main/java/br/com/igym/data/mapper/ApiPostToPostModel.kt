package br.com.igym.data.mapper

import br.com.igym.data.models.ApiPost
import br.com.igym.domain.entities.Post

object ApiPostToPostModel: Mapper<ApiPost, Post>() {
    override fun transform(i: ApiPost) = Post(
            userId =  i.userId,
            id = i.id,
            title = i.title,
            body = i.body
    )
}