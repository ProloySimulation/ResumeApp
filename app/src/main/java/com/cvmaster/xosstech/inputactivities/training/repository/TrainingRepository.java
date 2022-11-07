package com.cvmaster.xosstech.inputactivities.training.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.inputactivities.training.model.TrainingList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingRepository {

    private ArrayList<Training> trainings = new ArrayList<>();
    private MutableLiveData<List<Training>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public TrainingRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Training>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<TrainingList> call = service.getTrainingList(token);
        call.enqueue(new Callback<TrainingList>() {
            @Override
            public void onResponse(Call<TrainingList> call, Response<TrainingList> response) {

                TrainingList trainingList = response.body();
                if (trainingList != null && trainingList.getData() != null) {
                    trainings = (ArrayList<Training>) trainingList.getData();
                    mutableLiveData.setValue(trainings);
                }
            }
            @Override
            public void onFailure(Call<TrainingList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Training>> updateMutableLiveData(String token,int experienceId, Training training) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<TrainingList> call = service.updateTraining(token,experienceId,training);
        call.enqueue(new Callback<TrainingList>() {
            @Override
            public void onResponse(Call<TrainingList> call, Response<TrainingList> response) {

                TrainingList trainingList = response.body();
                if (trainingList != null && trainingList.getData() != null) {
                    trainings = (ArrayList<Training>) trainingList.getData();
                    mutableLiveData.setValue(trainings);
                }
            }
            @Override
            public void onFailure(Call<TrainingList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Training>> postTraining(String token,Training training) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<TrainingList> call = service.newTraining(token,training);
        call.enqueue(new Callback<TrainingList>() {
            @Override
            public void onResponse(Call<TrainingList> call, Response<TrainingList> response) {

                TrainingList trainingList = response.body();
                if (trainingList != null && trainingList.getData() != null) {
                    trainings = (ArrayList<Training>) trainingList.getData();
                    mutableLiveData.setValue(trainings);
                }
            }
            @Override
            public void onFailure(Call<TrainingList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Training>> deleteMutableLiveData(String token,int experienceId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<TrainingList> call = service.deleteTraining(token,experienceId);
        call.enqueue(new Callback<TrainingList>() {
            @Override
            public void onResponse(Call<TrainingList> call, Response<TrainingList> response) {

                TrainingList trainingList = response.body();
                if (trainingList != null && trainingList.getData() != null) {
                    trainings = (ArrayList<Training>) trainingList.getData();
                    mutableLiveData.setValue(trainings);
                }
            }
            @Override
            public void onFailure(Call<TrainingList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
