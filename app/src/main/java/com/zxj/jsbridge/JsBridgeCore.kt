package com.zxj.jsbridge

import android.webkit.JavascriptInterface
import com.google.gson.Gson
import java.net.URI

/**
 * 负责解析
 */
class JsBridgeCore {
    var webView: JsBridgeWebView? = null
    val JS_HANDLE_MESSAGE_FROM_JAVA =
        "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');"

    constructor(webView: JsBridgeWebView?) {
        this.webView = webView
    }

    //两边均采用json 进行通信,url 格式 activityOrService://moduleAlias/methodName
    @JavascriptInterface
    fun call(msg: String) {
        val jsRequest = Gson().fromJson<JsRequest>(msg, JsRequest::class.java)
        var jsResponse = createResponse(jsRequest)
        //callback 用来传给JSInterface，core 收到回调后处理这个结果，然后再给js
        jsResponse.jsCallback = object : JsCallback {
            override fun onResult(result: String) {
                replay(result, jsResponse)
            }
        }
        val uri = URI.create(jsRequest.url)
        when {
            JsConstants.SCHEME_SERVICE == uri.scheme -> {//调用基础服务
                for (jsInterface in webView!!.jsInterfaces) {
                    if (jsInterface.alias == uri.host) invokeMethod(
                        jsInterface,
                        uri.path,
                        jsRequest.body,
                        jsResponse.jsCallback
                    )
                }

            }
            JsConstants.SCHEME_ACTIVITY == uri.scheme -> {//调用界面

            }
            else -> {

            }
        }
    }

    //收到结果了，准备给js 答复
    private fun replay(result: String, jsResponse: JsResponse) {
        jsResponse.body = result
        webView?.evaluateJavascript(
            JS_HANDLE_MESSAGE_FROM_JAVA.format(Gson().toJson(jsResponse)),
            null)
    }

    //反射调用方法
    private fun invokeMethod(
        jsInterface: JsInterface,
        methodName: String,
        body: MutableMap<String, String>,
        jsCallback: JsCallback
    ) {
        val method = jsInterface.javaClass.getMethod(methodName, Map::class.java, JsCallback::class.java)
        method.invoke(body, jsCallback)
    }

    private fun createResponse(jsRequest: JsRequest): JsResponse {
        val jsResponse = JsResponse()
        jsResponse.sessionId = jsRequest.sessionId
        return jsResponse

    }
}