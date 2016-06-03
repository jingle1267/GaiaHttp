package com.ihongqiqu.http;

import android.content.Context;
import java.util.Map;

/**
 * 网络请求基础类
 * <p/>
 * Created by zhenguo on 5/31/16.
 */
public class BaseRequest<T> {

    private Context mContext;
    private String url;
    /**
     * 参数
     */
    private Map<String, String> mParams;
    /**
     * 回调
     */
    private BaseRequestListener<T> mRequestListener;
    /**
     * 请求方式
     */
    private int mMethod = BaseRequest.Method.GET;
    /**
     * 请求 TAG
     */
    private String mTag;
    /**
     * HTTP 请求 Header
     */
    private Map<String, String> mHeader;

    private RequestProxy mRequestProxy;

    public BaseRequest(Context context) {
        mContext = context;
        mRequestProxy = new RequestProxy(this);
    }

    /**
     * 发送请求
     */
    public void send() {
        if (mRequestListener != null) {
            mRequestListener.onStart();
        }

        mRequestProxy.buildRequest();
        RequestManager.getInstance(mContext).add(this);
    }

    /**
     * Supported request methods.
     */
    public interface Method {
        int GET = 0;
        int POST = 1;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public int getMethod() {
        return mMethod;
    }

    public void setMethod(int method) {
        mMethod = method;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public void setParams(Map<String, String> params) {
        mParams = params;
    }

    public BaseRequestListener<T> getRequestListener() {
        return mRequestListener;
    }

    public void setRequestListener(BaseRequestListener<T> requestListener) {
        mRequestListener = requestListener;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        this.mTag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeader() {
        return mHeader;
    }

    public void setHeader(Map<String, String> header) {
        mHeader = header;
    }

    public RequestProxy getRequestProxy() {
        return mRequestProxy;
    }

    public void setRequestProxy(RequestProxy requestProxy) {
        mRequestProxy = requestProxy;
    }
}
