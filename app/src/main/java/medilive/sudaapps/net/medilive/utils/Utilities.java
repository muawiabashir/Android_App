package medilive.sudaapps.net.medilive.utils;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import medilive.sudaapps.net.medilive.R;


/**
 * Created by Phaedra on 7/22/2015.
 */
public class Utilities {
    public static class AnimationUtilities{
        public static Animation setSlideOutChildBottomAnimation(Context c,final View view){
            Animation a;
            a = AnimationUtils.loadAnimation(c, R.anim.abc_slide_out_bottom);
            a.setInterpolator(new AccelerateInterpolator());
            a.setStartTime(SystemClock.uptimeMillis());
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            return a;
        }
        public static Animation setSlideInChildBottomAnimation(Context c,final View view){
            Animation a;
            a = AnimationUtils.loadAnimation(c, R.anim.abc_slide_in_bottom);
            a.setInterpolator(new AccelerateInterpolator());
            a.setStartTime(SystemClock.uptimeMillis());
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            return a;
        }
    }


}
