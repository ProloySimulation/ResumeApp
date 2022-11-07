package com.cvmaster.xosstech.inputactivities.reference.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.model.Reference;
import com.cvmaster.xosstech.inputactivities.reference.model.ReferenceList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferenceRepository {

    private ArrayList<Reference> references = new ArrayList<>();
    private MutableLiveData<List<Reference>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ReferenceRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Reference>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ReferenceList> call = service.getReferenceList(token);
        call.enqueue(new Callback<ReferenceList>() {
            @Override
            public void onResponse(Call<ReferenceList> call, Response<ReferenceList> response) {

                ReferenceList referenceList = response.body();
                if (referenceList != null && referenceList.getData() != null) {
                    references = (ArrayList<Reference>) referenceList.getData();
                    mutableLiveData.setValue(references);
                }
            }
            @Override
            public void onFailure(Call<ReferenceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Reference>> updateMutableLiveData(String token,int experienceId, Reference reference) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ReferenceList> call = service.updateReference(token,experienceId,reference);
        call.enqueue(new Callback<ReferenceList>() {
            @Override
            public void onResponse(Call<ReferenceList> call, Response<ReferenceList> response) {

                ReferenceList referenceList = response.body();
                if (referenceList != null && referenceList.getData() != null) {
                    references = (ArrayList<Reference>) referenceList.getData();
                    mutableLiveData.setValue(references);
                }
            }
            @Override
            public void onFailure(Call<ReferenceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Reference>> postReference(String token, Reference reference) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ReferenceList> call = service.newReference(token,reference);
        call.enqueue(new Callback<ReferenceList>() {
            @Override
            public void onResponse(Call<ReferenceList> call, Response<ReferenceList> response) {

                ReferenceList referenceList = response.body();
                if (referenceList != null && referenceList.getData() != null) {
                    references = (ArrayList<Reference>) referenceList.getData();
                    mutableLiveData.setValue(references);
                }
            }
            @Override
            public void onFailure(Call<ReferenceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Reference>> deleteMutableLiveData(String token,int experienceId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ReferenceList> call = service.deleteReference(token,experienceId);
        call.enqueue(new Callback<ReferenceList>() {
            @Override
            public void onResponse(Call<ReferenceList> call, Response<ReferenceList> response) {

                ReferenceList referenceList = response.body();
                if (referenceList != null && referenceList.getData() != null) {
                    references = (ArrayList<Reference>) referenceList.getData();
                    mutableLiveData.setValue(references);
                }
            }
            @Override
            public void onFailure(Call<ReferenceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
