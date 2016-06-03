package com.ihongqiqu.http;

/**
 * 网络请求回调接口
 * <p/>
 * Created by zhenguo on 5/31/16.
 */
public interface BaseRequestListener<T> {

    /**
     * 请求开始
     */
    void onStart();

    /**
     * 网路请求数据成功
     *
     * @param response
     */
    void onResponse(T response);

    /**
     * 网络发生错误
     *
     * @param err
     */
    void onError(String err);

    /**
     * 请求结束
     */
    void onStop();

}
