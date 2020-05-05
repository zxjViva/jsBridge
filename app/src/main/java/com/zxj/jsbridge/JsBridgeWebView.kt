package com.zxj.jsbridge

import android.content.Context
import android.webkit.WebView

class JsBridgeWebView(context: Context) : WebView(context) {
    var jsInterfaces = arrayListOf<JsInterface>()
    //提供js方法的类 应该是要初始化的，否则有些人用了成员变量会有问题
    fun registerJsInterface(jsInterface: JsInterface){
        jsInterfaces.add(jsInterface)
    }
}