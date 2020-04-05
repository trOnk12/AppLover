package com.example.applover.di.module

import com.example.applover.BuildConfig
import com.example.applover.data.network.service.LoginService
import com.example.applover.data.network.tsl.TLSSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val certificatePinner = CertificatePinner.Builder()
            .add("bench-api.applover.pl", "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
            .build()

        val clientBuilder = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideNetworkClient(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

}