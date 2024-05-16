package io.github.pvnk1u

interface HttpCallbackListener {

    /**
     * 当服务器成功响应我们请求的时候调用
     * response代表服务器响应的数据
     */
    fun onFinish(response: String)

    /**
     * 当进行网络操作出现错误的时候调用
     */
    fun onError(e: Exception)
}