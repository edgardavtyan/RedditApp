package com.ed.redditapp.lib.api.standard

import lombok.Getter
import org.json.JSONObject

class StandardJsonComment(val data: JSONObject, var indent: Int) {
    fun incrementIndent() = indent++
}