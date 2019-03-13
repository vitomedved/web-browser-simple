package com.example.webapprma

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.webkit.WebSettings
import android.webkit.WebViewClient





class MainActivity : AppCompatActivity() {

    private val url = "https://www.google.ru"
    private lateinit var prefs: SharedPreferences
    private lateinit var searchButton: ImageButton
    private lateinit var currentUrl: EditText
    private lateinit var webView: WebView
    private lateinit var favButton: ImageButton
    private lateinit var favSearchButton: ImageButton
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = this.getPreferences(Context.MODE_PRIVATE)

        editor = prefs.edit()
        editor.putString("homepage", url)
        editor.apply()

        webView = findViewById(R.id.webView)
        webView.loadUrl(url)

        searchButton = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            searchWeb()
        }

        currentUrl = findViewById(R.id.urlEditText)

        favButton = findViewById(R.id.favouriteButton)
        favButton.setOnClickListener{
            saveFavourite()
        }

        favSearchButton = findViewById(R.id.favouriteSearchButton)
        favSearchButton.setOnClickListener{
            searchFavouriteWebsite()
        }

        val webSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
        // Enable and setup web view cache
        /*webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.setAppCachePath(cacheDir.path)


        // Enable zooming in web view
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = true

        webSettings.loadsImagesAutomatically = true*/
    }

    private fun searchFavouriteWebsite() {
        val favUrl: String = prefs.getString("favourite", "https://www.google.ru")
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl(favUrl)
    }

    private fun saveFavourite() {

        val currUrl: String = currentUrl.text.toString()

        editor = prefs.edit()
        editor.putString("favourite", currUrl)
        editor.apply()
    }

    private fun searchWeb() {
        val currUrl: String = currentUrl.text.toString()
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl(currUrl)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            // If web view have back history,
            // then go to the web view back history
            webView.goBack()
        }
    }
}
