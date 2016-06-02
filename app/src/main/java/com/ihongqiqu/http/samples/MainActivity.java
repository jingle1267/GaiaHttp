package com.ihongqiqu.http.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ihongqiqu.http.BaseRequest;
import com.ihongqiqu.http.BaseRequestListener;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseRequest request = new BaseRequest(this);
        request.setUrl("http://www.mocky.io/v2/5185415ba171ea3a00704eed");
        request.setRequestListener(new BaseRequestListener() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onResponse(Object response) {
                Log.d(TAG, "onResponse: " + response);
            }

            @Override
            public void onError(String err) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onStop() {
                Log.d(TAG, "onStop: ");;
            }
        });
        request.send();

    }
}
