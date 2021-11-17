package com.ddona.data.paser

import com.ddona.data.model.Comment
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL

object CommentParser {

    fun getAllComment(link: String): List<Comment> {
        val comments = arrayListOf<Comment>()

        val url = URL(link)
        val connection = url.openConnection()
        val inputStream = connection.getInputStream()

        val br = BufferedReader(InputStreamReader(inputStream))
        var line = br.readLine()
        val data = StringBuilder()
        while (line != null) {
            data.append(line)
            line = br.readLine()
        }

        val root = JSONArray(data.toString())
        for (index in 0 until root.length()) {
            val item = root[index] as JSONObject
            val postId = item.getInt("postId")
            val id = item.getInt("id")
            val name = item.getString("name")
            val email = item.getString("email")
            val body = item.getString("body")
            val comment = Comment(postId, id, name, email, body)
            comments.add(comment)
        }

        return comments
    }

}