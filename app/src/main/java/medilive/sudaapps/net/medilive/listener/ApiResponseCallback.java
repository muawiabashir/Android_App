package medilive.sudaapps.net.medilive.listener;

import com.android.volley.VolleyError;

/**
 * Created by Adil on 16/09/15.
 */
public class ApiResponseCallback {
    public ApiResponseCallback(Object jsonObject, String url, ApiResponseCallbackListener volleyResponseCallback){
        volleyResponseCallback.onSuccessFullResponse(jsonObject,url);
    }
    public ApiResponseCallback(VolleyError error, String url, ApiResponseCallbackListener volleyResponseCallbackHandler){
        volleyResponseCallbackHandler.onErrorResponse(error,url);
    }
    public interface ApiResponseCallbackListener {
        public void onSuccessFullResponse(Object jsonObject, String url);
        public void onErrorResponse(VolleyError error, String url);
    }
}
