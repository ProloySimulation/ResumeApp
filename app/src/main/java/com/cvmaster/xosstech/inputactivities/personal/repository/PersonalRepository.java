package com.cvmaster.xosstech.inputactivities.personal.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.model.PersonalInformation;
import com.cvmaster.xosstech.inputactivities.personal.model.PersonalInfoList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalRepository {
    private ArrayList<PersonalInformation> informations = new ArrayList<>();
    private MutableLiveData<List<PersonalInformation>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public PersonalRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<PersonalInformation>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<PersonalInfoList> call = service.getPersonalInfo(token);
        call.enqueue(new Callback<PersonalInfoList>() {
            @Override
            public void onResponse(Call<PersonalInfoList> call, Response<PersonalInfoList> response) {

                PersonalInfoList personalInfoList = response.body();
                if (personalInfoList != null && personalInfoList.getData() != null) {
                    informations = (ArrayList<PersonalInformation>) personalInfoList.getData();
                    mutableLiveData.setValue(informations);
                }
            }
            @Override
            public void onFailure(Call<PersonalInfoList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<PersonalInformation>> updateMutableLiveData(String token,int personalInfoId, PersonalInformation information) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<PersonalInfoList> call = service.updatePersonalInfo(token,personalInfoId,information);
        call.enqueue(new Callback<PersonalInfoList>() {
            @Override
            public void onResponse(Call<PersonalInfoList> call, Response<PersonalInfoList> response) {

                PersonalInfoList personalInfoList = response.body();
                if (personalInfoList != null && personalInfoList.getData() != null) {
                    informations = (ArrayList<PersonalInformation>) personalInfoList.getData();
                    mutableLiveData.setValue(informations);
                }
            }
            @Override
            public void onFailure(Call<PersonalInfoList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<PersonalInformation>> postInfo(String token,PersonalInformation information) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<PersonalInfoList> call = service.newPersonalInfo(token,information);
        call.enqueue(new Callback<PersonalInfoList>() {
            @Override
            public void onResponse(Call<PersonalInfoList> call, Response<PersonalInfoList> response) {

                PersonalInfoList personalInfoList = response.body();
                if (personalInfoList != null && personalInfoList.getData() != null) {
                    informations = (ArrayList<PersonalInformation>) personalInfoList.getData();
                    mutableLiveData.setValue(informations);
                }
            }
            @Override
            public void onFailure(Call<PersonalInfoList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<PersonalInformation>> deleteMutableLiveData(String token,int personalInfoId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<PersonalInfoList> call = service.deletePersonalInfo(token,personalInfoId);
        call.enqueue(new Callback<PersonalInfoList>() {
            @Override
            public void onResponse(Call<PersonalInfoList> call, Response<PersonalInfoList> response) {

                PersonalInfoList personalInfoList = response.body();
                if (personalInfoList != null && personalInfoList.getData() != null) {
                    informations = (ArrayList<PersonalInformation>) personalInfoList.getData();
                    mutableLiveData.setValue(informations);
                }
            }
            @Override
            public void onFailure(Call<PersonalInfoList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
