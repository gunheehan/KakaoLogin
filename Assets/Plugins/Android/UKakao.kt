package com.unity3d.player

import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.unity3d.player.UnityPlayer

class UKakao {
    fun KakaoLogin() {
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                //Log.e(TAG, "로그인 실패", error)
                println("로그인 실패 : $error")
                                UnityPlayer.UnitySendMessage("AndroidPlugin","PrintResult",error.toString())
            }
            else if (token != null) {
                //Log.i(TAG, "로그인 성공 ${token.accessToken}")
                println("로그인 성공 ${token.accessToken}")
                                UnityPlayer.UnitySendMessage("AndroidPlugin","PrintResult",token.accessToken)
            }
        }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(UnityPlayer.currentActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(UnityPlayer.currentActivity, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(UnityPlayer.currentActivity, callback = callback)
        }
    }
}