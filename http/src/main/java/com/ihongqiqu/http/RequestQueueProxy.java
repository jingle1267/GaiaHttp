package com.ihongqiqu.http;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 网络请求队列代理
 *
 * Created by zhenguo on 6/2/16.
 */
public class RequestQueueProxy {

    private static RequestQueueProxy mRequestQueueProxy;

    private Call mCall;
    OkHttpClient mOkHttpClient;

    public RequestQueueProxy() {
        mOkHttpClient = new OkHttpClient();
    }

    public static RequestQueueProxy getInstance() {
        if (mRequestQueueProxy == null) {
            mRequestQueueProxy = new RequestQueueProxy();
        }
        return mRequestQueueProxy;
    }

    public void add(final BaseRequest request) {
        mCall = mOkHttpClient.newCall(request.getRequestProxy().getRequest());
            mCall.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    request.getRequestListener().onError(call.toString());
                    request.getRequestListener().onStop();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    request.getRequestListener().onResponse(response.body().string());
                    request.getRequestListener().onStop();
                }
            });
    }

    public void remove(String tag) {

    }

    public void remove(BaseRequest request) {
        mCall = mOkHttpClient.newCall(request.getRequestProxy().getRequest());
        mCall.cancel();
    }

    public void removeAll() {

    }

}
