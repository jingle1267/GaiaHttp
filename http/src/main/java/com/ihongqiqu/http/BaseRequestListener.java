package com.ihongqiqu.http;

/**
 * 网络请求回调接口
 * <p/>
 * Created by zhenguo on 5/31/16.
 */
public interface BaseRequestListener<T> {

    void onStart();

    void onResponse(T response);

    void onError(String err);

    void onStop();

}
