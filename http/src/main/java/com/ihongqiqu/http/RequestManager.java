package com.ihongqiqu.http;

import android.content.Context;

/**
 * 网络请求管理类
 * <p/>
 * Created by zhenguo on 6/2/16.
 */
public class RequestManager {

    private Context mContext;
    private static RequestManager mRequestManager;
    private RequestQueueProxy  mRequestQueueProxy;

    public RequestManager(Context context) {
        mContext = context;
        mRequestQueueProxy = getRequestQueue();
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (mRequestManager == null) {
            mRequestManager = new RequestManager(context);
        }
        return mRequestManager;
    }
    public void add(BaseRequest request) {
        mRequestQueueProxy.add(request);
    }

    public void remove(String tag) {
        mRequestQueueProxy.remove(tag);
    }

    public void remove(BaseRequest request) {
        mRequestQueueProxy.remove(request);
    }

    public void removeAll() {
        mRequestQueueProxy.removeAll();
    }


    public RequestQueueProxy getRequestQueue() {
        return RequestQueueProxy.getInstance();
    }

}
