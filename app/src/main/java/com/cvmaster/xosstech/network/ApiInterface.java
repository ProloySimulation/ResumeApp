package com.cvmaster.xosstech.network;


import com.cvmaster.xosstech.network.model.ModelResponses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zubrein on 7/15/19.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("check_subscription.php")
    Call<ModelResponses> check_subscription(@Field("user_mobile") String user_mobile
    );

    @FormUrlEncoded
    @POST("subscription.php")
    Call<ModelResponses> subscription(@Field("user_mobile") String user_mobile
    );

    @FormUrlEncoded
    @POST("cass.php")
    Call<ModelResponses> charging(@Field("user_mobile") String user_mobile,
                                  @Field("charge") String charge
    );


}
