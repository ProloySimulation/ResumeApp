package com.cvmaster.xosstech.inputactivities.personal.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.cvmaster.xosstech.model.PersonalInformation;
import com.cvmaster.xosstech.inputactivities.personal.repository.PersonalRepository;

import java.util.List;

public class PersonalInfoViewModel extends AndroidViewModel {

    private PersonalRepository personalRepository;

    public PersonalInfoViewModel(@NonNull Application application) {
        super(application);
        personalRepository = new PersonalRepository(application);
    }
    public LiveData<List<PersonalInformation>> getAllInfos(String token) {
        return personalRepository.getMutableLiveData(token);
    }
    public LiveData<List<PersonalInformation>> postAllInfo(String token,PersonalInformation information) {
        return personalRepository.postInfo(token,information);
    }
    public LiveData<List<PersonalInformation>> updateInfo(String token,int infoId,PersonalInformation information) {
        return personalRepository.updateMutableLiveData(token,infoId,information);
    }
    public LiveData<List<PersonalInformation>> deleteInfo(String token,int infoId) {
        return personalRepository.deleteMutableLiveData(token,infoId);
    }
}
