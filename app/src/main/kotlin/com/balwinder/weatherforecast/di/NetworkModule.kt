package com.balwinder.weatherforecast.di

import com.weather.core.BuildConfig
import com.weather.core.data.repository.remote.APIservice
import com.weather.core.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy.ACCEPT_ALL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun provideRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.balwinder.weatherforecast.BuildConfig.END_POINT)
            .client(getClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(ACCEPT_ALL)

        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }


        builder.addNetworkInterceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
            chain.proceed(requestBuilder.build())
        }

        builder.cookieJar(JavaNetCookieJar(cookieManager))

        try {
            // Create a trust manager that does not validate certificate chains

            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf<X509Certificate>()
                }

                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }
            })


            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {

                    return true//hostname.contains(BuildConfig.BASE_URL);

                }
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }

        builder.connectTimeout(200, TimeUnit.SECONDS)
        builder.readTimeout(200, TimeUnit.SECONDS)
        builder.writeTimeout(200, TimeUnit.SECONDS)

        return builder.build()
    }


    @PerApplication
    @Provides
    fun provideApiService(retrofit: Retrofit): APIservice {
        return retrofit.create(APIservice::class.java)
    }


}