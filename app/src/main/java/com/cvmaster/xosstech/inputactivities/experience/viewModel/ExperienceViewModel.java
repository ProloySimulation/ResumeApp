package com.cvmaster.xosstech.inputactivities.experience.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Experience;
import com.cvmaster.xosstech.inputactivities.experience.repository.ExperienceRepository;

import java.util.List;

public class ExperienceViewModel extends AndroidViewModel {

    private ExperienceRepository experienceRepository;

    public ExperienceViewModel(@NonNull Application application) {
        super(application);
        experienceRepository = new ExperienceRepository(application);
    }
    public LiveData<List<Experience>> getAllUsers(String token) {
        return experienceRepository.getMutableLiveData(token);
    }
    public LiveData<List<Experience>> postAllUsers(String token,Experience experience) {
        return experienceRepository.postExperience(token,experience);
    }
    public LiveData<List<Experience>> updateUsers(String token,int experienceId,Experience experience) {
        return experienceRepository.updateMutableLiveData(token,experienceId,experience);
    }
    public LiveData<List<Experience>> deleteExperience(String token,int experienceId) {
        return experienceRepository.deleteMutableLiveData(token,experienceId);
    }
}
