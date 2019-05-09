package com.ericcode.rxhttpdemo.net.api;

import com.ericcode.rxhttpdemo.net.bean.HotTopics;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface V2exRepo {

    //https://www.v2ex.com/api/topics/hot.json
    @GET("api/topics/hot.json")
    Observable<List<HotTopics>> getHotTopics();

    //https://www.v2ex.com/api/topics/hot.json
    @GET("api/topics/latest.json")
    Observable<List<HotTopics>> getLatestTopics();
}
