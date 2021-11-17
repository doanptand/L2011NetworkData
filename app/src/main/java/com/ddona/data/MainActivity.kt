package com.ddona.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddona.data.adapter.NewsAdapter
import com.ddona.data.databinding.ActivityMainBinding
import com.ddona.data.model.News

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val news = arrayListOf<News>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        news.add(News("Abcdjdh djhdj", "sdfasdfasdf", "https://image.24h.com.vn/upload/4-2021/images/2021-11-17/1637127569-423-thumbnail-width640height480.jpg","sdfasdf","ádfasdf"))
        news.add(News("Abcdjdh djhdj", "sdfasdfasdf", "https://image.24h.com.vn/upload/4-2021/images/2021-11-17/1637127569-423-thumbnail-width640height480.jpg","sdfasdf","ádfasdf"))
        news.add(News("Abcdjdh djhdj", "sdfasdfasdf", "https://image.24h.com.vn/upload/4-2021/images/2021-11-17/1637127569-423-thumbnail-width640height480.jpg","sdfasdf","ádfasdf"))
        val adapter = NewsAdapter(news)
        binding.rvNews.adapter = adapter
    }
}