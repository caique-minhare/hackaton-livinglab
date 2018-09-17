package br.com.igym.data.api

import br.com.igym.data.models.ApiPost
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

class ApiClient(private val dataSource: FuckyouDataSource) {
    fun getPosts(id: Int): Single<ApiPost> {
        return makeRequest(dataSource.getId(id))
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map(Response<T>::body)
        }
    }

    private fun <T> makeRequest(request: Single<Response<T>>): Single<T> {
        return request.compose(unwrap())
    }
}