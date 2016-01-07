package medilive.sudaapps.net.medilive;

/**
 * Created by muawia.ibrahim on 10/16/2015.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


public class ImageSlider extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    JSONParser jParser = new JSONParser();
    JSONParser jsonParser = new JSONParser();
    static final String URL = "http://sudaapps.net/android/medilife/food_api/get_images.php";
    JSONArray Learn = null;
    private SliderLayout mDemoSlider;

    private static final String TAG_PRODUCT = "product";
    private ArrayList<Category> ImagesList;
    static final String TAG_SUCCESS = "success";
    static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_descrption = "descrption";
    static final String TAG_LEARN = "learn";
    private GetImages task;
    JSONArray products = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageslider);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        ImagesList = new ArrayList<>();
        task = new GetImages();
        task.execute();
        HashMap<String, String> url_maps = new HashMap<String, String>();

        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
     //   file_maps.put("شراب حبة صباح ", R.drawable.s);
    //    file_maps.put("كبسولات", R.drawable.s1);
    //    file_maps.put("دريب", R.drawable.s2);
    //    file_maps.put("رقاد", R.drawable.s3);
    //    file_maps.put("Game of Thrones", R.drawable.s4);
    //    file_maps.put("شراب حبة صباح ", R.drawable.s);
    //    file_maps.put("كبسولات", R.drawable.s1);
    //    file_maps.put("دريب", R.drawable.s2);
    //    file_maps.put("رقاد", R.drawable.s3);
    //    file_maps.put("Game of Thrones", R.drawable.s4);
    //    file_maps.put("شراب حبة صباح ", R.drawable.s);
    //    file_maps.put("كبسولات", R.drawable.s1);
    //    file_maps.put("دريب", R.drawable.s2);
    //    file_maps.put("رقاد", R.drawable.s3);
     //   file_maps.put("Game of Thrones", R.drawable.s4);
    /*    for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }*/


        //    ListView l = (ListView)findViewById(R.id.transformers);
        //    l.setAdapter(new TransformerAdapter(this));
        //    l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //         @Override
        //         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //             mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
        //              Toast.makeText(ImageSlider.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
        //          }
        //      });


    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //       case R.id.action_custom_indicator:
            //         mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
            //          break;
            //      case R.id.action_custom_child_animation:
            ///         mDemoSlider.setCustomAnimation(new ChildAnimationExample());
            //      break;
            //case R.id.action_restore_default:
            //  mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            //mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            // break;
            // case R.id.action_github:
            //   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
            // startActivity(browserIntent);
            // break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public class GetImages extends AsyncTask<Void, Void, Integer> implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

        private JSONObject json;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * Getting product details in background thread
         */
        protected Integer doInBackground(Void... param) {


            int success;
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", TAG_ID));

                JSONObject jsonobj = jsonParser.makeHttpRequest(URL, "GET", params);


                success = jsonobj.getInt(TAG_SUCCESS);
                if (success == 1) {

                    JSONArray productObj = jsonobj.getJSONArray(TAG_PRODUCT);
                    for (int i = 0; i < productObj.length(); i++) {
                        json = productObj.getJSONObject(i);
                        String name = json.getString(TAG_NAME);
                        String descr = json.getString(TAG_descrption);
                        String image = json.getString(TAG_IMAGE);





                            // Storing each json item in variable



                    }
                    return 1;
                } else {
                    return 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;

            }

        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(Integer result) {

            if (result == 1) {

                final TextSliderView textSliderView = new TextSliderView(getApplication());


                textSliderView
                        .description(TAG_NAME)
                        .image(TAG_IMAGE)
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", TAG_descrption);

                    mDemoSlider.addSlider(textSliderView);
                    mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                    mDemoSlider.setDuration(4000);
                    mDemoSlider.addOnPageChangeListener(this);


            }


            }


        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }

        @Override
        public void onSliderClick(BaseSliderView baseSliderView) {

        }
    }
    }
