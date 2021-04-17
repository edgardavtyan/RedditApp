package com.ed.redditapp.lib.api

interface SubReddit {
    val name: String
    val title: String
    val description: String
    val iconUrl: String
    val subsCount: Int
}