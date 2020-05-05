package com.zxj.jsbridge.simple

import com.zxj.jsbridge.JsCallback
import com.zxj.jsbridge.JsInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginJsInterface : JsInterface {
    override var alias: String
        get() = "login"
        set(value) {}

    fun getLoginInfo(jsCallback: JsCallback) {
        GlobalScope.launch {
            Thread.sleep(3000)
            jsCallback.onResult("just test")
        }
    }
}