package com.example.yogesh16991.test_proj;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by HP on 20-04-2015.
 */
public class EventDetailsJSon{
    private Context mcontext;
    List<Map<String,?>> eventsList;

    public List<Map<String, ?>> getMoviesList() {
        return eventsList;
    }



    public List<Map<String, ?>> getEventsList() {
        return eventsList;
    }

    public int getSize(){
        return eventsList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < eventsList.size()){
            return (HashMap) eventsList.get(i);
        } else return null;
    }



    public EventDetailsJSon(Context context) throws JSONException {
        String description = null;
        String title = null;
        String desit = null;
        JSONArray eventsJsonArray = null;
        JSONObject eventJsonObj = null;
        eventsList = new ArrayList<Map<String,?>>();
        String eventsArray = loadEventJSONFromAsset(context);
        eventsJsonArray = new JSONArray(eventsArray);
        for(int i = 0; i <eventsJsonArray.length();i++){
            eventJsonObj = (JSONObject) eventsJsonArray.get(i);
            if(eventJsonObj != null) {
                title = (String) eventJsonObj.get("title");
                desit = (String) eventJsonObj.get("Desit");
            }
            eventsList.add(createEvent(title,desit));

        }
    }


    private HashMap createEvent(String title,String desit) {
        HashMap event = new HashMap();
        event.put("title",title);
        event.put("desit",desit);
        return event;
    }

    public String loadEventJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.police);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
