package apps.training.searcher.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import apps.training.searcher.data.Results
import apps.training.searcher.data.SearchResult
import apps.training.searcher.database.SongsDatabase
import apps.training.searcher.database.SongsDatabase.Companion.instance
import apps.training.searcher.remote.APiClient
import apps.training.searcher.utils.Extensions.or
import apps.training.searcher.utils.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SearchModel : ViewModel() {

    private var dbClient : SongsDatabase ?= null

    fun instance(query : Any?, context: Context) : LiveData<List<Results>> {
        if (dbClient == null){
            dbClient = context.instance()
        }
        this.setResponse(query)
        return dbClient?.results()?.getResults("$query")!!
    }

    private fun setResponse(query: Any?){
        APiClient.apiService.search(query)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : DisposableSingleObserver<Response<SearchResult>>(){
                override fun onSuccess(t: Response<SearchResult>) {
                    t.body()?.let {
                        it.results?.let { resultData -> dbClient?.results()?.insertAll(resultData) }
                    }
                }

                override fun onError(e: Throwable) {
                   Logger.error( tag= "SearchViewModel", e = e, msg = e.message.or(e.localizedMessage))
                }

            })
    }
}