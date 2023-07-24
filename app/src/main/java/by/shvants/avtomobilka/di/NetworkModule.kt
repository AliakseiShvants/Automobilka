package by.shvants.avtomobilka.network

import by.shvants.avtomobilka.data.CarApi
import com.google.gson.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

val networkModule = module {
    single<File> { androidContext().cacheDir }
    single { Cache(directory = get(), maxSize = 4 * 1024 * 1024) }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single {
        OkHttpClient.Builder()
            .cache(get())
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(get<HttpLoggingInterceptor>())

                try {
                    val trustAllCerts: Array<TrustManager> = arrayOf(
                        object : X509TrustManager {
                            override fun checkClientTrusted(
                                chain: Array<out X509Certificate>?,
                                authType: String?
                            ) {
                            }

                            override fun checkServerTrusted(
                                chain: Array<out X509Certificate>?,
                                authType: String?
                            ) {
                            }

                            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                        }
                    )

                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null, trustAllCerts, SecureRandom())

                    val sslSocketFactory = sslContext.socketFactory

                    if (trustAllCerts.isNotEmpty() && trustAllCerts.first() is X509TrustManager) {
                        sslSocketFactory(
                            sslSocketFactory,
                            trustAllCerts.first() as X509TrustManager
                        )
                        hostnameVerifier { _, _ -> true }
                    }

                } catch (e: Exception) {
                }
            }
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .header("User-Agent", "Android")
                    .header("Accept", "application/json")
                    .build()

                return@addInterceptor it.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single<Gson> {
        GsonBuilder().apply {
            setLenient()
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }.create()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .baseUrl("http://am111.05.testing.place")
            .client(get())
            .build()
    }
    single<CarApi> {
        get<Retrofit>().create()
    }

}