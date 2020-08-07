package apps.training.searcher.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import apps.training.searcher.R
import apps.training.searcher.adapter.SongTrackAdapter
import apps.training.searcher.data.SearchResult
import apps.training.searcher.remote.APiClient
import apps.training.searcher.utils.Extensions.hide
import apps.training.searcher.utils.Extensions.or
import apps.training.searcher.utils.Extensions.show
import apps.training.searcher.utils.Logger.debug
import apps.training.searcher.utils.Logger.error
import apps.training.searcher.viewmodel.SearchModel
import com.google.gson.JsonObject
import com.kaopiz.kprogresshud.KProgressHUD
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_empty_page.*
import retrofit2.Response

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    private val trackAdapter : SongTrackAdapter by lazy { SongTrackAdapter() }
    private val searchModel : SearchModel by lazy { ViewModelProvider(this)[SearchModel::class.java] }

    private val progressDialog : KProgressHUD by lazy {
        KProgressHUD(this)
            .apply {
                setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)!!
                setLabel("Loading")
                setCancellable(false)
                setAnimationSpeed(2)
                setDimAmount(3f)
            }
    }
    companion object{
        const val OLD_QUERY = "old_query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.getString(MainActivity.OLD_QUERY)?.let {
            search_query_text.setQuery(it,true)
            searchQuery(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        search_query_text.query?.let { outState.putString(MainActivity.OLD_QUERY, it.toString()) }
    }

    override fun onStart() {
        super.onStart()
        search_query_text.setOnQueryTextListener(this)
        swipe_refresh.setOnRefreshListener(this)
        result_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        result_view.adapter = trackAdapter
        result_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx <= dy){
                    search_card.show()
                } else {
                    search_card.hide()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun searchQuery(query: String?){
        if (swipe_refresh.isRefreshing){
            swipe_refresh.isRefreshing = false
        }
        progressDialog.show()
        searchModel.instance(query,this@MainActivity)
            .observeForever {
                progressDialog.dismiss()
                it.let { listOfData -> trackAdapter.loadTracks(listOfData) }
                if (it.isNotEmpty()){ emptyView.hide() } else { emptyView.show() }
                error(tag = "SearchData",msg = it)
            }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchQuery(query)
        return query != null
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return newText != null
    }

    override fun onRefresh() {
        searchQuery("${search_query_text.query}")
    }
}