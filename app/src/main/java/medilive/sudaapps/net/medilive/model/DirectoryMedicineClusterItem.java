package medilive.sudaapps.net.medilive.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Adil on 01/01/2016.
 */
public class DirectoryMedicineClusterItem implements ClusterItem {
    private final LatLng mPosition;

    public DirectoryMedicineClusterItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
