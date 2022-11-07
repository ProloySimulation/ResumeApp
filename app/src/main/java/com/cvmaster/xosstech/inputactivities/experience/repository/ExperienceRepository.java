package com.cvmaster.xosstech.inputactivities.experience.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.model.Experience;
import com.cvmaster.xosstech.inputactivities.experience.model.ExperienceList;
import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExperienceRepository {

    private ArrayList<Experience> experiences = new ArrayList<>();
    private MutableLiveData<List<Experience>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ExperienceRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Experience>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ExperienceList> call = service.getExperienceList(token);
        call.enqueue(new Callback<ExperienceList>() {
            @Override
            public void onResponse(Call<ExperienceList> call, Response<ExperienceList> response) {

                ExperienceList experienceList = response.body();
                if (experienceList != null && experienceList.getData() != null) {
                    experiences = (ArrayList<Experience>) experienceList.getData();
                    mutableLiveData.setValue(experiences);
                }
            }
            @Override
            public void onFailure(Call<ExperienceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Experience>> updateMutableLiveData(String token,int experienceId, Experience experience) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ExperienceList> call = service.updateExperience(token,experienceId,experience);
        call.enqueue(new Callback<ExperienceList>() {
            @Override
            public void onResponse(Call<ExperienceList> call, Response<ExperienceList> response) {

                ExperienceList experienceList = response.body();
                if (experienceList != null && experienceList.getData() != null) {
                    experiences = (ArrayList<Experience>) experienceList.getData();
                    mutableLiveData.setValue(experiences);
                }
            }
            @Override
            public void onFailure(Call<ExperienceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Experience>> postExperience(String token,Experience experience) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ExperienceList> call = service.newExperience(token,experience);
        call.enqueue(new Callback<ExperienceList>() {
            @Override
            public void onResponse(Call<ExperienceList> call, Response<ExperienceList> response) {

                ExperienceList experienceList = response.body();
                if (experienceList != null && experienceList.getData() != null) {
                    experiences = (ArrayList<Experience>) experienceList.getData();
                    mutableLiveData.setValue(experiences);
                }
            }
            @Override
            public void onFailure(Call<ExperienceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Experience>> deleteMutableLiveData(String token,int experienceId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ExperienceList> call = service.deleteExperience(token,experienceId);
        call.enqueue(new Callback<ExperienceList>() {
            @Override
            public void onResponse(Call<ExperienceList> call, Response<ExperienceList> response) {

                ExperienceList experienceList = response.body();
                if (experienceList != null && experienceList.getData() != null) {
                    experiences = (ArrayList<Experience>) experienceList.getData();
                    mutableLiveData.setValue(experiences);
                }
            }
            @Override
            public void onFailure(Call<ExperienceList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
