package com.ddona.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.ddona.data.adapter.NewsAdapter
import com.ddona.data.databinding.ActivityMainBinding
import com.ddona.data.model.News
import com.ddona.data.paser.CommentParser
import com.ddona.data.paser.Director
import com.ddona.data.paser.NewsParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val news = arrayListOf<News>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(news)
        binding.rvNews.adapter = adapter


        lifecycleScope.launch(Dispatchers.IO) {
            val items = NewsParser.getNews("https://cdn.24h.com.vn/upload/rss/trangchu24h.rss")
            news.addAll(items)
            val comments =
                CommentParser.getAllComment("https://jsonplaceholder.cypress.io/comments")
            Log.d("doanpt", "Comment size is ${comments.size}")
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }

    }
}