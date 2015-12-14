package com.nianzhen.soundrecording.http;

import com.nianzhen.soundrecording.bean.User;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2015/12/10.
 */
public interface HttpService {

    @GET("user")
    public rx.Observable<User> getUser(@Query("userId") String userId);

}
