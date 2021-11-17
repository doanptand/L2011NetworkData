package com.ddona.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddona.data.adapter.CommentAdapter
import com.ddona.data.databinding.ActivityCommentBinding
import com.ddona.data.model.Comment
import com.ddona.data.paser.CommentParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentActivity : AppCompatActivity() {
    private val comments = arrayListOf<Comment>()
    private lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val commentAdapter = CommentAdapter(comments)


        binding.rvComments.adapter = commentAdapter


        binding.rvComments.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch(Dispatchers.IO) {
            val items =
                CommentParser.getAllComment("https://jsonplaceholder.cypress.io/comments")
            Log.d("doanpt", "Comment size is ${items.size}")
            comments.addAll(items)
            withContext(Dispatchers.Main) {
                commentAdapter.notifyDataSetChanged()
            }
        }
    }
}