package com.zxj.jsbridge

/**
 * request
 * type
 * url
 * 仿照http 写request
 *
 */
class JsRequest {
    var version = ""
    var sessionId: String = ""
    var type: String = ""
    var url: String = ""
    var body: MutableMap<String, String> = mutableMapOf()
    var header: MutableMap<String, String> = mutableMapOf()

}