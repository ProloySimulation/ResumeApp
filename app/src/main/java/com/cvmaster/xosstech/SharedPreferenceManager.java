package com.cvmaster.xosstech;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static Context mContext;

    public static final String SHARED_PREFERENCE_MANAGER = "shared_preference_manager";
    public static final String KEY_USER_MOBILE_NUMBER = "key_user_mobile_number";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_USER_Token = "key_user_token";
    public static final String KEY_IMAGE_ENCODE = "key_image_encode";
    public static final String KEY_CURRENT_DATE = "key_current_date";
    public static final String KEY_AD_COUNT = "key_ad_count";

    private SharedPreferenceManager(Context context){
        mContext = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }


    public boolean UserLoggedInfo(String user_mobile_number,String user_id,String user_token,String key_image_token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_MOBILE_NUMBER,user_mobile_number);
        editor.putString(KEY_USER_ID,user_id);
        editor.putString(KEY_USER_Token,user_token);
        editor.putString(KEY_IMAGE_ENCODE,key_image_token);
        editor.apply();

        return true;
    }

    public boolean IsUserLoggedIn(){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_MOBILE_NUMBER,null)!=null){
            return true;
        }
        if(sharedPreferences.getString(KEY_USER_ID,null)!=null){
            return true;
        }
        if(sharedPreferences.getString(KEY_USER_Token,null)!=null){
            return true;
        }
        if(sharedPreferences.getString(KEY_IMAGE_ENCODE,null)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean LogOut(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public int GetCurrentDate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_CURRENT_DATE,0);
    }

    public boolean SetCurrentDate(int date){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_CURRENT_DATE,date);
        editor.apply();
        return true;
    }

    public boolean SetAdCount(int count){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_AD_COUNT,count);
        editor.apply();
        return true;
    }

    public int GetAdCount(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_AD_COUNT,0);
    }

    public String GetUserMobileNumber(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_MOBILE_NUMBER,null);
    }

    public String GetUserId(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID,null);
    }

    public String GetUserToken(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_Token,null);
    }

    public String GetUserImage(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_IMAGE_ENCODE,null);
    }

}
