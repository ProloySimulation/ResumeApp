package com.cvmaster.xosstech.Notification;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        sendNewTokenToServer(FirebaseMessaging.getInstance().getToken());
    }

    private void sendNewTokenToServer(Task<String> token) {
        Log.d("TOKEN",String.valueOf(token));
    }

}
