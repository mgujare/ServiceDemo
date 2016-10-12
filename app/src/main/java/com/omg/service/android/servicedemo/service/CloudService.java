package com.omg.service.android.servicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mgujare on 10/11/16.
 */

public class CloudService extends IntentService {

    private static final String TAG = CloudService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public CloudService() {
        super("CloudService");
    }

    @Override

    protected void onHandleIntent(Intent intent) {

        String urlPath = null;
        JSONObject jsonObject = null;
        if (intent != null) {
            urlPath = intent.getStringExtra("urlpath");
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlPath)
                .build();
        //Make call.
        try {
            Response response = client.newCall(request).execute();
            jsonObject = new JSONObject(response.body().string());
        } catch (Exception e)  {
            Log.e(TAG, "Exception in network call " + e.getMessage());
        }
        String data = jsonObject.optString("date");
        publishDataFromCloud(data);
    }

    public void publishDataFromCloud(String date){
        Intent intent = new Intent(NOTIFICATION_SERVICE);
        intent.putExtra("date", date);
        sendBroadcast(intent);
    }
}
