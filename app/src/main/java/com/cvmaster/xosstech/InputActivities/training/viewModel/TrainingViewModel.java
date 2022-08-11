package com.cvmaster.xosstech.InputActivities.training.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.InputActivities.training.repository.TrainingRepository;

import java.util.List;

public class TrainingViewModel extends AndroidViewModel {

    private TrainingRepository trainingRepository;

    public TrainingViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
    }
    public LiveData<List<Training>> getAllTrainings(String token) {
        return trainingRepository.getMutableLiveData(token);
    }
    public LiveData<List<Training>> postAllTraining(String token,Training training) {
        return trainingRepository.postTraining(token,training);
    }
    public LiveData<List<Training>> updateTraining(String token,int experienceId,Training training) {
        return trainingRepository.updateMutableLiveData(token,experienceId,training);
    }
    public LiveData<List<Training>> deleteTraining(String token,int experienceId) {
        return trainingRepository.deleteMutableLiveData(token,experienceId);
    }
}
