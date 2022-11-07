package com.cvmaster.xosstech.inputactivities.additionalinfo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Additional;
import com.cvmaster.xosstech.inputactivities.additionalinfo.repository.AdditionalRepository;

import java.util.List;

public class AdditionalViewModel extends AndroidViewModel {

    private AdditionalRepository additionalRepository;

    public AdditionalViewModel(@NonNull Application application) {
        super(application);
        additionalRepository = new AdditionalRepository(application);
    }

    public LiveData<List<Additional>> getAllAdditional(String token)
    {
        return additionalRepository.getAdditional(token);
    }

    public LiveData<List<Additional>> newAdditional(String token, Additional additional)
    {
        return additionalRepository.postAdditional(token,additional);
    }

    public LiveData<List<Additional>> updateAdditional(String token,int addionalId, Additional additional)
    {
        return additionalRepository.updateAdditional(token,addionalId,additional);
    }
}
