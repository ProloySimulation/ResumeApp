package com.cvmaster.xosstech.InputActivities.project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.InputActivities.project.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {

    private ProjectRepository projectRepository;

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        projectRepository = new ProjectRepository(application);
    }
    public LiveData<List<Project>> getAllProjects(String token) {
        return projectRepository.getMutableLiveData(token);
    }
    public LiveData<List<Project>> postProject(String token, Project project) {
        return projectRepository.postProject(token,project);
    }
    public LiveData<List<Project>> updateProject(String token,int experienceId,Project project) {
        return projectRepository.updateMutableLiveData(token,experienceId,project);
    }
    public LiveData<List<Project>> deleteProject(String token,int experienceId) {
        return projectRepository.deleteMutableLiveData(token,experienceId);
    }
}
