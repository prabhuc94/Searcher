package apps.training.searcher.remote

import apps.training.searcher.BuildConfig
import apps.training.searcher.utils.Extensions.or
import apps.training.searcher.utils.Logger.debug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ClientHelper {
    const val CONNECTION_TIME_OUT : Long = 30000
    val TIME_UNIT = TimeUnit.SECONDS

    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {
                }

                override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier {hostname, session -> true })
            builder.connectTimeout(CONNECTION_TIME_OUT, TIME_UNIT)
            builder.readTimeout(CONNECTION_TIME_OUT, TIME_UNIT)
            builder.writeTimeout(CONNECTION_TIME_OUT, TIME_UNIT)
            builder.retryOnConnectionFailure(true)
            builder.addNetworkInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json") //If we use this then we can't use MultiPart Data
                    .addHeader("User-Agent", BuildConfig.APPLICATION_ID)
                    .build()
                chain.proceed(newRequest)
            }
            builder.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            return builder
        } catch (e: Exception) {
            debug( tag = this.javaClass.simpleName, msg = e.localizedMessage?.or(e.message) )
            throw RuntimeException()
        }
    }
}