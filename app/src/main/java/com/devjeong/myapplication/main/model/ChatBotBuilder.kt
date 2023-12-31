package com.devjeong.myapplication.main.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import com.devjeong.myapplication.BuildConfig

object ChatBotBuilder {
    var chatBotApi: ChatbotApi

    private const val chatServer = BuildConfig.CHATBOT_API
    init{
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(chatServer)   //요청 보내는 API 서버 url /로 끝나야 함
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create() )
            .addConverterFactory(GsonConverterFactory.create())//Gson을 역직렬화
            .build()

        chatBotApi=retrofit.create(ChatbotApi::class.java)
    }
}
