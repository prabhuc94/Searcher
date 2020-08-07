package apps.training.searcher.remote

import apps.training.searcher.BuildConfig
import apps.training.searcher.remote.ClientHelper.getUnsafeOkHttpClient
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APiClient {

    private var retrofit : Retrofit? = null

    private val instance : Retrofit
        get() {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    val apiService = instance.create(APiService::class.java)
}