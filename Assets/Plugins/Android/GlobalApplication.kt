package com.unity3d.player

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this,"NATIVE APP KEY")
    }
}