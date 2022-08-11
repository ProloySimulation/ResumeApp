package com.cvmaster.xosstech.InputActivities.project.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cvmaster.xosstech.network.ApiClient;
import com.cvmaster.xosstech.network.ApiInterface;
import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.InputActivities.project.data.ProjectList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {

    private ArrayList<Project> projects = new ArrayList<>();
    private MutableLiveData<List<Project>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ProjectRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Project>> getMutableLiveData(String token) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ProjectList> call = service.getProjectList(token);
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {

                ProjectList projectList = response.body();
                if (projectList != null && projectList.getData() != null) {
                    projects = (ArrayList<Project>) projectList.getData();
                    mutableLiveData.setValue(projects);
                }
            }
            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Project>> updateMutableLiveData(String token,int experienceId, Project project) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ProjectList> call = service.updateProject(token,experienceId,project);
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {

                ProjectList projectList = response.body();
                if (projectList != null && projectList.getData() != null) {
                    projects = (ArrayList<Project>) projectList.getData();
                    mutableLiveData.setValue(projects);
                }
            }
            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Project>> postProject(String token,Project project) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ProjectList> call = service.newProject(token,project);
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {

                ProjectList projectList = response.body();
                if (projectList != null && projectList.getData() != null) {
                    projects = (ArrayList<Project>) projectList.getData();
                    mutableLiveData.setValue(projects);
                }
            }
            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Project>> deleteMutableLiveData(String token,int experienceId) {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ProjectList> call = service.deleteProject(token,experienceId);
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {

                ProjectList projectList = response.body();
                if (projectList != null && projectList.getData() != null) {
                    projects = (ArrayList<Project>) projectList.getData();
                    mutableLiveData.setValue(projects);
                }
            }
            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
