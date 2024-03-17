using System;
using System.Collections;
using System.Collections.Generic;
using System.Net.Mime;
using UnityEngine;
using UnityEngine.UI;

public class AndroidPlugin : MonoBehaviour
{
    private AndroidJavaObject kotlin;
    [SerializeField] private Text text; 
    void Start()
    {
        kotlin = new AndroidJavaObject("com.unity3d.player.UKakao");
    }

    public void OnclickLogin()
    {
        result = PrintResult;
        kotlin.Call("KakaoLogin");
    }

    private Action<string> result;
    public void PrintResult(string token)
    {
        Debug.Log(token);
    }

    public void SetText(string _text)
    {
        text.text = _text;
    }
}
