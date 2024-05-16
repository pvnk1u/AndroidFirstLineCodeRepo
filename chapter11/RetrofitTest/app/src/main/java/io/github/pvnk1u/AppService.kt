package io.github.pvnk1u

import retrofit2.Call
import retrofit2.http.GET

/**
 * 通常Retrofit的接口文件建议以具体的功能种类名开头，并以Service结尾，这是一种比较好的命名习惯。
 */
interface AppService {

    /**
     * 接口地址是get_data.json，HTTP RequestMethod是Get
     * 返回结果是List<App>
     */
    @GET("get_data.json")
    fun getAppData(): Call<List<App>>
}