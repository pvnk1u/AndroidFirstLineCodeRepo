package io.github.pvnk1u

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView : WebView = findViewById(R.id.webView)
        /**
         * 通过WebView的getSettings()方法可以设置一些浏览器属性
         *
         * 这里设置启用JavaScript
         */
        webView.settings.javaScriptEnabled=true
        /**
         * 调用了WebView的setWebViewClient()方法，并传入
         * 了一个WebViewClient的实例。这段代码的作用是，当需要从一个网页跳转到另一个网页时，
         * 我们希望目标网页仍然在当前WebView中显示，而不是打开系统浏览器。
         */
        webView.webViewClient = WebViewClient()
        /**
         * 调用WebView的loadUrl()方法，并将网址传入
         */
        webView.loadUrl("https://www.bing.com")
    }
}