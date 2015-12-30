package medilive.sudaapps.net.medilive.activity;

import android.os.Bundle;

import com.android.volley.VolleyError;

import medilive.sudaapps.net.medilive.R;
import medilive.sudaapps.net.medilive.listener.ApiResponseCallback;

/**
 * Created by muawia.ibrahim on 8/25/2015.
 */
public class Directory extends AppCompatBaseActivity implements ApiResponseCallback.ApiResponseCallbackListener{
    private static final String GET_MEDICINES_LIST_URL="http://sudaapps.net/android/medilife/food_api/get_All_medicin.php";
    private static final String GET_MEDICINES_DIRECTION_LIST_URL="http://sudaapps.net/android/medilife/food_api/medi_serach.php?med_name=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
    }
    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void onSuccessFullResponse(Object jsonObject, String url) {

    }

    @Override
    public void onErrorResponse(VolleyError error, String url) {

    }
}