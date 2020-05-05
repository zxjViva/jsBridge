package com.zxj.jsbridge

import com.google.gson.annotations.Expose

class JsResponse {
    var version = ""
    var sessionId: String = ""
    var status: String = ""
    var header: MutableMap<String, String> = mutableMapOf()
    var body: String = ""

    @Expose
    lateinit var jsCallback: JsCallback //不参与序列化
}