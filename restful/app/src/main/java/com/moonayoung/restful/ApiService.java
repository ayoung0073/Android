package com.moonayoung.restful;

import java.util.ArrayList;

import okhttp3.Challenge;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/diarys") // 챌린지목록(추천챌린지포함)
    Call<ArrayList<Diary>> getDiarys();
    @GET("/") // 챌린지목록(추천챌린지포함)
    Call<String> getTest();
    @POST("/diarys/") // 사진업로드
    Call<Diary> postDiary(@Body Diary diary);

}
