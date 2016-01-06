package com.nianzhen.soundrecording.http;

import com.nianzhen.soundrecording.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Administrator on 2015/12/9.
 */
public class HttpClient {
    public static final long CONNECTION_TIMEOUT_MILLIS = 4 * 1000L;
    public static final long READ_TIMEOUT_MILLIS = 10 * 1000L;

    public static final HttpService getInstance = new HttpClient().getmService();

    private static final String HOST_PATH = "";//api地址

    private HttpService mService;

    private HttpClient() {
        if (mService == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            okHttpClient.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            // TODO: 2015/12/9 这里以后要做签名验证
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(BuildConfig.IS_DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade req) {
                            // Public headers
                        }
                    })
                    .setClient(new OkClient(okHttpClient))
                    .setEndpoint(HOST_PATH)
                    .build();

            mService = restAdapter.create(HttpService.class);
        }
    }

    public HttpService getmService() {
        return mService;
    }

}
