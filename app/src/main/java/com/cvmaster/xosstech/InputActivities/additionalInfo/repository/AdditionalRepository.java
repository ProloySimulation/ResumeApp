package com.cvmaster.xosstech.InputActivities.additionalInfo.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.model.Additional;
import com.cvmaster.xosstech.InputActivities.additionalInfo.model.AdditionalList;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdditionalRepository {

    private ArrayList<Additional> additionals = new ArrayList<>();
    private MutableLiveData<List<Additional>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public AdditionalRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Additional>> getAdditional(String token)
    {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<AdditionalList> call = service.getAdditional(token);
        call.enqueue(new Callback<AdditionalList>() {
            @Override
            public void onResponse(Call<AdditionalList> call, Response<AdditionalList> response) {
                AdditionalList additionalList = response.body();
                if(additionalList!=null && additionalList.getData() != null)
                {
                    additionals = (ArrayList<Additional>) additionalList.getData();
                    mutableLiveData.postValue(additionals);
                }
            }

            @Override
            public void onFailure(Call<AdditionalList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Additional>> updateAdditional(String token,int additionalId, Additional additional)
    {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<AdditionalList> call = service.updateAdditional(token,additionalId,additional);
        call.enqueue(new Callback<AdditionalList>() {
            @Override
            public void onResponse(Call<AdditionalList> call, Response<AdditionalList> response) {
                AdditionalList additionalList = response.body();
                if(additionalList!=null && additionalList.getData() != null)
                {
                    additionals = (ArrayList<Additional>) additionalList.getData();
                    mutableLiveData.postValue(additionals);
                }
            }

            @Override
            public void onFailure(Call<AdditionalList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Additional>> postAdditional(String token,Additional additional)
    {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<AdditionalList> call = service.newAdditional(token,additional);
        call.enqueue(new Callback<AdditionalList>() {
            @Override
            public void onResponse(Call<AdditionalList> call, Response<AdditionalList> response) {
                AdditionalList additionalList = response.body();
                if(additionalList!=null && additionalList.getData() != null)
                {
                    additionals = (ArrayList<Additional>) additionalList.getData();
                    mutableLiveData.postValue(additionals);
                }
            }

            @Override
            public void onFailure(Call<AdditionalList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
