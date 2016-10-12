package com.example.coolweather.util;

/**
 * Created by 臻 on 2016/10/12.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
