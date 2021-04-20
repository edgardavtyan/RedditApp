package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.Comment

class StandardComment(jsonComment: StandardJsonComment) : Comment {
    override val username = jsonComment.data.getString("author")
    override val body = jsonComment.data.getString("body_html")
    override val timestamp = jsonComment.data.getLong("created_utc")
    override val points = jsonComment.data.getInt("ups")
    override val indent = jsonComment.indent
}