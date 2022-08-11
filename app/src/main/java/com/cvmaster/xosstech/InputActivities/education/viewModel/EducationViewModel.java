package com.cvmaster.xosstech.InputActivities.education.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Education;
import com.cvmaster.xosstech.InputActivities.education.repository.EducationRepository;

import java.util.List;

public class EducationViewModel extends AndroidViewModel {

    private EducationRepository educationRepository;

    public EducationViewModel(@NonNull Application application) {
        super(application);
        educationRepository = new EducationRepository(application);
    }
    public LiveData<List<Education>> getAllEducation(String token) {
        return educationRepository.getMutableLiveData(token);
    }
    public LiveData<List<Education>> postAllEducation(String token,Education education) {
        return educationRepository.postEducation(token,education);
    }
    public LiveData<List<Education>> updateEducation(String token,int experienceId,Education education) {
        return educationRepository.updateMutableLiveData(token,experienceId,education);
    }
    public LiveData<List<Education>> deleteEducation(String token,int experienceId) {
        return educationRepository.deleteMutableLiveData(token,experienceId);
    }
}
