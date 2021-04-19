package com.ed.redditapp

fun String.format(vararg args:Object): String {
    return java.lang.String.format(this, args)
}
