package com.ed.redditapp

class ResourceReader {
    fun get(path: String): String {
        return this.javaClass.classLoader!!
                .getResourceAsStream(path)
                .bufferedReader()
                .use { it.readText() }
    }
}