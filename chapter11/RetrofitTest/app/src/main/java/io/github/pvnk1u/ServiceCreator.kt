package io.github.pvnk1u

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "http://192.168.0.103/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)


    /**
     * 使用kotlin的泛型实化功能
     *
     * 可以通过以下代码更简洁地获取AppService接口的动态代理对象
     * val appService = ServiceCreator.create<AppService>()
     */
    inline fun <reified T> create(): T = create(T::class.java)
}