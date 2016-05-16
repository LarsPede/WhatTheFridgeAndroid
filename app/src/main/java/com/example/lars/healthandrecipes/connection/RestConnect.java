package com.example.lars.healthandrecipes.connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Lars on 12-04-2016.
 */
public class RestConnect {

    String ip = "10.16.78.114";
    String url = "http://" + ip + ":8080/WTF/rest/calculationserver";

    public JSONArray getIngredients(){
        url = url + "/allingredients";
        String resp;
        JSONObject jsonObjectResp = null;
        JSONArray jsonArrayResp =  null;
        Response response;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {

            response = client.newCall(request).execute();
            resp = response.body().string();
            jsonObjectResp = new JSONObject(resp);
            jsonArrayResp = jsonObjectResp.getJSONArray("ingredients");
            url = "http://" + ip + ":8080/WTF/rest/calculationcerver";

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArrayResp;
    }


    //return list
    public JSONArray postWhatCanIMake(JSONObject json){
        url = url + "/whatcanimake";
        String resp;
        JSONObject jsonObjectResp = null;
        JSONArray jsonArrayResp =  null;
        Response response;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {

            response = client.newCall(request).execute();
            resp = response.body().string();
            System.out.println(resp);
            jsonObjectResp = new JSONObject(resp);
            jsonArrayResp = jsonObjectResp.getJSONArray("ingredients");
            url = "http://" + ip + ":8080/WTF/rest/calculationcerver";
            System.out.println("LarsPeter " + jsonArrayResp);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArrayResp;
    }

    public JSONArray getRecipes(){
        url = url + "/allrecipes";
        String resp;
        JSONArray jsonResp = null;
        Response response;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {

            response = client.newCall(request).execute();
            url = "http://" + ip + ":8080/WTF/rest/calculationserver";
            resp = response.body().string();
            jsonResp = new JSONArray(resp);

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }

    public void postIngredients(JSONObject json){
        url = url + "/addingredient";
        String resp;
        JSONObject jsonObjectResp = null;
        JSONArray jsonArrayResp =  null;
        Response response;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {

            response = client.newCall(request).execute();
            resp = response.body().string();
            System.out.println(resp);
            url = "http://" + ip + ":8080/WTF/rest/calculationcerver";
            System.out.println("LarsPeter " + jsonArrayResp);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
