package com.cvmaster.xosstech.inputactivities.reference.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Reference;
import com.cvmaster.xosstech.inputactivities.reference.repository.ReferenceRepository;

import java.util.List;

public class ReferenceViewModel extends AndroidViewModel {

    private ReferenceRepository referenceRepository;

    public ReferenceViewModel(@NonNull Application application) {
        super(application);
        referenceRepository = new ReferenceRepository(application);
    }
    public LiveData<List<Reference>> getAllReferences(String token) {
        return referenceRepository.getMutableLiveData(token);
    }
    public LiveData<List<Reference>> postAllReference(String token,Reference reference) {
        return referenceRepository.postReference(token,reference);
    }
    public LiveData<List<Reference>> updateReference(String token,int experienceId,Reference reference) {
        return referenceRepository.updateMutableLiveData(token,experienceId,reference);
    }
    public LiveData<List<Reference>> deleteReference(String token,int experienceId) {
        return referenceRepository.deleteMutableLiveData(token,experienceId);
    }
}
