package com.cvmaster.xosstech.network;


import com.cvmaster.xosstech.model.Additional;
import com.cvmaster.xosstech.inputactivities.additionalinfo.model.AdditionalList;
import com.cvmaster.xosstech.model.Education;
import com.cvmaster.xosstech.inputactivities.education.model.EducationList;
import com.cvmaster.xosstech.model.Experience;
import com.cvmaster.xosstech.inputactivities.experience.model.ExperienceList;
import com.cvmaster.xosstech.model.Suggestion;
import com.cvmaster.xosstech.network.model.ModelResponses;
import com.cvmaster.xosstech.model.PersonalInformation;
import com.cvmaster.xosstech.inputactivities.personal.model.PersonalInfoList;
import com.cvmaster.xosstech.model.Project;
import com.cvmaster.xosstech.inputactivities.project.data.ProjectList;
import com.cvmaster.xosstech.model.Reference;
import com.cvmaster.xosstech.inputactivities.reference.model.ReferenceList;
import com.cvmaster.xosstech.model.Training;
import com.cvmaster.xosstech.inputactivities.training.model.TrainingList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zubrein on 7/15/19.
 */

public interface ApiInterface {

    //Personal

    @POST("api/public/api/infos")
    Call<PersonalInfoList> getPersonalInfo(@Header("Authorization")String token);

    @POST("api/public/api/info")
    Call<PersonalInfoList> newPersonalInfo(@Header ("Authorization")String token,@Body PersonalInformation PersonalInformation);

    @POST("api/public/api/info/update/{id}")
    Call<PersonalInfoList> updatePersonalInfo(@Header ("Authorization")String token, @Path("id")int id, @Body PersonalInformation PersonalInformation);

    @DELETE("api/public/api/info/delete/{id}")
    Call<PersonalInfoList> deletePersonalInfo(@Header ("Authorization")String token, @Path("id")int id);

    // Experience

    @POST("api/public/api/experiences")
    Call<ExperienceList> getExperienceList(@Header("Authorization")String token);

    @POST("api/public/api/experience")
    Call<ExperienceList> newExperience(@Header ("Authorization")String token,@Body Experience experience);

    @POST("api/public/api/experience/update/{id}")
    Call<ExperienceList> updateExperience(@Header ("Authorization")String token, @Path("id")int id, @Body Experience experience);

    @DELETE("api/public/api/experience/delete/{id}")
    Call<ExperienceList> deleteExperience(@Header ("Authorization")String token, @Path("id")int id);

    //Education

    @POST("api/public/api/educations")
    Call<EducationList> getEducationList(@Header("Authorization")String token);

    @POST("api/public/api/education")
    Call<EducationList> newEducation(@Header ("Authorization")String token,@Body Education education);

    @POST("api/public/api/education/update/{id}")
    Call<EducationList> updateEducation(@Header ("Authorization")String token, @Path("id")int id, @Body Education education);

    @DELETE("api/public/api/education/delete/{id}")
    Call<EducationList> deleteEducation(@Header ("Authorization")String token, @Path("id")int id);

    //Projects

    @POST("api/public/api/projects")
    Call<ProjectList> getProjectList(@Header("Authorization")String token);

    @POST("api/public/api/project")
    Call<ProjectList> newProject(@Header ("Authorization")String token,@Body Project project);

    @POST("api/public/api/project/update/{id}")
    Call<ProjectList> updateProject(@Header ("Authorization")String token, @Path("id")int id, @Body Project project);

    @DELETE("api/public/api/project/delete/{id}")
    Call<ProjectList> deleteProject(@Header ("Authorization")String token, @Path("id")int id);

    //Reference

    @POST("api/public/api/references")
    Call<ReferenceList> getReferenceList(@Header("Authorization")String token);

    @POST("api/public/api/reference")
    Call<ReferenceList> newReference(@Header ("Authorization")String token,@Body Reference reference);

    @POST("api/public/api/reference/update/{id}")
    Call<ReferenceList> updateReference(@Header ("Authorization")String token, @Path("id")int id, @Body Reference reference);

    @DELETE("api/public/api/reference/delete/{id}")
    Call<ReferenceList> deleteReference(@Header ("Authorization")String token, @Path("id")int id);

    //Training

    @POST("api/public/api/trainings")
    Call<TrainingList> getTrainingList(@Header("Authorization")String token);

    @POST("api/public/api/training")
    Call<TrainingList> newTraining(@Header ("Authorization")String token,@Body Training training);

    @POST("api/public/api/training/update/{id}")
    Call<TrainingList> updateTraining(@Header ("Authorization")String token, @Path("id")int id, @Body Training training);

    @DELETE("api/public/api/training/delete/{id}")
    Call<TrainingList> deleteTraining(@Header ("Authorization")String token, @Path("id")int id);

    //Additional

    @POST("api/public/api/additions")
    Call<AdditionalList> getAdditional(@Header("Authorization")String token);

    @POST("api/public/api/addition")
    Call<AdditionalList> newAdditional(@Header ("Authorization")String token,@Body Additional additional);

    @POST("api/public/api/addition/update/{id}")
    Call<AdditionalList> updateAdditional(@Header ("Authorization")String token, @Path("id")int id, @Body Additional additional);

    // Suggestions

    @GET("api/public/api/suggetion/{key}")
    Call<List<Suggestion>> getEducationSuggestion(@Path("key")String key);

    // Nagad


    // BdApps
    @FormUrlEncoded
    @POST("xossapp/check_subscription.php")
    Call<ModelResponses> check_subscription(@Field("user_mobile") String user_mobile
    );

    @FormUrlEncoded
    @POST("xossapp/subscription.php")
    Call<ModelResponses> subscription(@Field("user_mobile") String user_mobile
    );

    @FormUrlEncoded
    @POST("xossapp/cass.php")
    Call<ModelResponses> charging(@Field("user_mobile") String user_mobile,
                                  @Field("charge") String charge
    );
}
