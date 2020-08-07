package apps.training.searcher.remote

import apps.training.searcher.data.SearchResult
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APiService {

    @GET("search")
    fun search(@Query("term") keyword : Any ?) : Single<Response<SearchResult>>
}