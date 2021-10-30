package com.cvmaster.xosstech;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static Context mContext;

    public static final String SHARED_PREFERENCE_MANAGER = "shared_preference_manager";
    public static final String KEY_USER_MOBILE_NUMBER = "key_user_mobile_number";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_USER_Token = "key_user_token";

    private SharedPreferenceManager(Context context){
        mContext = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }


    public boolean UserLoggedInfo(String user_mobile_number,String user_id,String user_token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_MANAGER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_MOBILE_NUMBER,user_mobile_number);
        editor.putString(KEY_USER_ID,user_id);
        editor.putString(KEY_USER_Token,user_token);
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

}
