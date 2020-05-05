package com.zxj.jsbridge

class JsConstants {
    companion object{
        val STATUS_OK = 200
        val STATUS_NOT_FIND = 404
        val STATUS_PARSE_ERROR = 500

        //请求类型 区分同步异步
        val TYPE_ASYNC = "async"//异步
        val TYPE_SYNC = "sync"//同步

        val SCHEME_SERVICE = "service"
        val SCHEME_ACTIVITY = "activity"
    }
}