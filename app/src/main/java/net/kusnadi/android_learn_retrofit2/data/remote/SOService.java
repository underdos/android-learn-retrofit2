package net.kusnadi.android_learn_retrofit2.data.remote;

import net.kusnadi.android_learn_retrofit2.data.model.SOAnswerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 11/09/17.
 */

public interface SOService {
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers(@Query("tagged") String tags);
}
