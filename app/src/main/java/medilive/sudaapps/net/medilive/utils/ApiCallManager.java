package medilive.sudaapps.net.medilive.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import medilive.sudaapps.net.medilive.listener.ApiResponseCallback;

/**
 * Created by Faris Jameel on 8/25/2015.
 */
public class ApiCallManager {

    public static void makeGetRequestForJSON(final Context context, final String url, JSONObject jsonObject, final ApiResponseCallback.ApiResponseCallbackListener callbackHandler) {

//        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        apiResponse.onSuccess(jsonObject,url);
                        callbackHandler.onSuccessFullResponse(jsonObject,url);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        callbackHandler.onErrorResponse(volleyError, url);
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("Accept","application/json");
                params.put("Accept-Encoding", "utf-8");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(jsonRequest);

    }

    public static void makePostRequestForJson(final Context context, final String url,JSONObject jsonObject, final ApiResponseCallback.ApiResponseCallbackListener callbackHandler) {

//        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        apiResponse.onSuccess(jsonObject,url);
                        callbackHandler.onSuccessFullResponse(jsonObject,url);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        callbackHandler.onErrorResponse(volleyError, url);
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("Accept","application/json");
                params.put("Accept-Encoding", "utf-8");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(jsonRequest);

    }

    public static void makeApiCallForJSONArray(final Context context, final String url, final ApiResponseCallback.ApiResponseCallbackListener callbackHandler) {

        JsonArrayRequest jsonRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonObject) {
//                        apiResponse.onSuccess(jsonObject,url);
                        callbackHandler.onSuccessFullResponse(jsonObject,url);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        callbackHandler.onErrorResponse(volleyError, url);
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("Accept","application/json");
                params.put("Accept-Encoding", "utf-8");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(jsonRequest);

    }

}
