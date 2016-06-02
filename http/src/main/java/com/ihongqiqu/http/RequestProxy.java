package com.ihongqiqu.http;

import android.text.TextUtils;
import android.util.Log;
import java.util.Map;
import java.util.Set;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

/**
 * 网络请求代理
 * <p/>
 * Created by zhenguo on 6/2/16.
 */
public class RequestProxy {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private BaseRequest mBaseRequest;
    private Request mRequest;

    public RequestProxy(BaseRequest baseRequest) {
        mBaseRequest = baseRequest;
    }

    public BaseRequest getBaseRequest() {
        return mBaseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        mBaseRequest = baseRequest;
    }

    public Request getRequest() {
        return mRequest;
    }

    public void setRequest(Request request) {
        mRequest = request;
    }

    public void buildRequest() {
        if (mBaseRequest == null) {
            if (BuildConfig.DEBUG) Log.d("RequestProxy", "mBaseRequest == null");
            return;
        }
        Request.Builder builder = new Request.Builder()
                .url(mBaseRequest.getUrl());
        if (mBaseRequest.getParams() != null && !mBaseRequest.getParams().isEmpty()) {
            RequestBody body = getRequestBody(mBaseRequest.getParams());
            String method = getMethod(mBaseRequest.getMethod());

            builder.method(method, body);
        }
        if (!TextUtils.isEmpty(mBaseRequest.getTag())) {
            builder.tag(mBaseRequest.getTag());
        }

        if (mBaseRequest.getHeader() != null && !mBaseRequest.getHeader().keySet().isEmpty()) {
            Set<String> headerSet = mBaseRequest.getHeader().keySet();
            for (String k : headerSet) {
                String v = (String) mBaseRequest.getHeader().get(k);
                builder.addHeader(k, v);
            }
        }

        mRequest = builder.build();

        // RequestManager.getInstance(mBaseRequest.get)

    }

    private RequestBody getRequestBody(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject(params);
        return RequestBody.create(JSON, jsonObject.toString());
    }

    private String getMethod(int method) {
        String methodStr = "GET";
        switch (method) {
            case 0:
                methodStr = "GET";
                break;
            case 1:
                methodStr = "POST";
                break;
        }
        return methodStr;
    }

}
