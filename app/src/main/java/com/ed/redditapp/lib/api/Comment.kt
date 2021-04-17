package com.ed.redditapp.lib.api

interface Comment {
    val username: String
    val body: String
    val timestamp: Long
    val points: Int
    val indent: Int
}