package com.cvmaster.xosstech.InputActivities.education.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.model.Education;
import com.cvmaster.xosstech.InputActivities.education.model.EducationList;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationRepository {

    private ArrayList<Education> educations = new ArrayList<>();
    private MutableLiveData<List<Education>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public EducationRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Education>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<EducationList> call = service.getEducationList(token);
        call.enqueue(new Callback<EducationList>() {
            @Override
            public void onResponse(Call<EducationList> call, Response<EducationList> response) {

                EducationList educationList = response.body();
                if (educationList != null && educationList.getData() != null) {
                    educations = (ArrayList<Education>) educationList.getData();
                    mutableLiveData.setValue(educations);
                }
            }
            @Override
            public void onFailure(Call<EducationList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Education>> updateMutableLiveData(String token,int experienceId, Education education) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<EducationList> call = service.updateEducation(token,experienceId,education);
        call.enqueue(new Callback<EducationList>() {
            @Override
            public void onResponse(Call<EducationList> call, Response<EducationList> response) {

                EducationList educationList = response.body();
                if (educationList != null && educationList.getData() != null) {
                    educations = (ArrayList<Education>) educationList.getData();
                    mutableLiveData.setValue(educations);
                }
            }
            @Override
            public void onFailure(Call<EducationList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Education>> postEducation(String token,Education education) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<EducationList> call = service.newEducation(token,education);
        call.enqueue(new Callback<EducationList>() {
            @Override
            public void onResponse(Call<EducationList> call, Response<EducationList> response) {

                EducationList educationList = response.body();
                if (educationList != null && educationList.getData() != null) {
                    educations = (ArrayList<Education>) educationList.getData();
                    mutableLiveData.setValue(educations);
                }
            }
            @Override
            public void onFailure(Call<EducationList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Education>> deleteMutableLiveData(String token,int experienceId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<EducationList> call = service.deleteEducation(token,experienceId);
        call.enqueue(new Callback<EducationList>() {
            @Override
            public void onResponse(Call<EducationList> call, Response<EducationList> response) {

                EducationList educationList = response.body();
                if (educationList != null && educationList.getData() != null) {
                    educations = (ArrayList<Education>) educationList.getData();
                    mutableLiveData.setValue(educations);
                }
            }
            @Override
            public void onFailure(Call<EducationList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
