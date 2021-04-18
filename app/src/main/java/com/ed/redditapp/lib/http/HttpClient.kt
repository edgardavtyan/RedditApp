package com.ed.redditapp.lib.http

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class HttpClient {
    fun get(url: String): String {
        val urlConn = URL(url)
        val connection = urlConn.openConnection()
        connection.connect()
        return getData(connection)
    }

    fun getJson(url: String): JSONObject {
        return JSONObject(get(url))
    }

    fun getArray(url: String): JSONArray {
        return JSONArray(get(url))
    }

    fun getBitmap(url: String): Bitmap {
        val urlConn = URL(url)
        val conn = urlConn.openConnection()
        conn.doInput = true
        conn.connect()
        return BitmapFactory.decodeStream(conn.inputStream)
    }

    private fun getData(conn: URLConnection): String {
        val strBuilder = StringBuilder()
        val inStream = BufferedInputStream(conn.inputStream)
        val bufReader = BufferedReader(InputStreamReader(inStream))
        while (true) {
            val line = bufReader.readLine() ?: break
            strBuilder.append(line)
        }
        return strBuilder.toString()
    }
}