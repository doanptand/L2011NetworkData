package com.ddona.data.paser

import com.ddona.data.model.News
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

object NewsParser {

    fun getNews(link: String): List<News> {
        val news = arrayListOf<News>()

        val url = URL(link)
        val connection = url.openConnection()
        val inputStream = connection.getInputStream()

        val parserFactory = XmlPullParserFactory.newInstance()
        val xmlPullParser = parserFactory.newPullParser()
        xmlPullParser.setInput(inputStream, "utf-8")

        var eventType = xmlPullParser.eventType
        var item = News()
        var text = ""

        while (eventType != XmlPullParser.END_DOCUMENT) {
            //doc data
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val tagName = xmlPullParser.name
                    when (tagName) {
                        "item" -> {
                            item = News()
                        }
                        "img" -> {
                            val thumb = xmlPullParser.getAttributeValue(null, "src")
                            item.thumb = thumb
                        }
                    }
                }
                XmlPullParser.TEXT -> {
                    text = xmlPullParser.text
                }
                XmlPullParser.END_TAG -> {
                    val tagName = xmlPullParser.name
                    when (tagName) {
                        "item" -> {
                            news.add(item)
                        }
                        "title" -> {
                            item.title = text
                        }
                        "description" -> {
                            item.description = text
                        }
                        "pubDate" -> {
                            item.pubDate = text
                        }
                        "link" -> {
                            item.detailLink = text
                        }
                    }
                }
            }
            //next the
            xmlPullParser.next()
        }


        return news
    }
}