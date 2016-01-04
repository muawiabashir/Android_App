package medilive.sudaapps.net.medilive.utils;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import medilive.sudaapps.net.medilive.model.DirectoryMedicine;

/**
 * Created by Adil on 02/01/2016.
 */
public class MapClusterItemRanderer extends DefaultClusterRenderer<DirectoryMedicine> {

    private ClusterManager<DirectoryMedicine> mClusterManager;
    Context mContext;

    public MapClusterItemRanderer(Context context, GoogleMap map, ClusterManager<DirectoryMedicine> clusterManager) {
        super(context, map, clusterManager);
        mContext=context;
        mClusterManager=clusterManager;
    }

    @Override
    protected void onBeforeClusterItemRendered(DirectoryMedicine item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        markerOptions.title(item.getMedicineAddress()).snippet(item.getMedicinePharmacyContact()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    }

    @Override
    protected void onClusterRendered(Cluster<DirectoryMedicine> cluster, Marker marker) {
        super.onClusterRendered(cluster, marker);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() > 10;
    }

//    private Bitmap writeTextOnDrawable(int drawableId, String text) {
//
//        Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), drawableId)
//                .copy(Bitmap.Config.ARGB_8888, true);
//
//        Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);
//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setColor(Color.parseColor("#FF4081"));
//        paint.setTypeface(tf);
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(convertToPixels(mContext, 11));
//
//        Rect textRect = new Rect();
//        paint.getTextBounds(text, 0, text.length(), textRect);
//
//        Canvas canvas = new Canvas(bm);
//
//        //If the text is bigger than the canvas , reduce the font size
//        if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
//            paint.setTextSize(convertToPixels(mContext, 7));        //Scaling needs to be used for different dpi's
//
//        //Calculate the positions
//        int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset
//
//        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
//        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
//
//        canvas.drawText(text, xPos, yPos, paint);
//
//        return  bm;
//    }
//
//
//
//    public static int convertToPixels(Context context, int nDP)
//    {
//        final float conversionScale = context.getResources().getDisplayMetrics().density;
//
//        return (int) ((nDP * conversionScale) + 0.5f) ;
//
//    }

}
