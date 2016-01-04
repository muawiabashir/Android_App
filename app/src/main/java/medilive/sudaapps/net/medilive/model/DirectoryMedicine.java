package medilive.sudaapps.net.medilive.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Adil on 30/12/2015.
 */
public class DirectoryMedicine implements ClusterItem {

    String medicineID;
    String medicineName;
    String medicineAddress;
    String medicineCity;
    String medicineLng;
    String medicineLat;
    String medicinePharmacyContact;


    public DirectoryMedicine() {
    }

    public String getMedicinePharmacyContact() {
        return medicinePharmacyContact;
    }

    public void setMedicinePharmacyContact(String medicinePharmacyContact) {
        this.medicinePharmacyContact = medicinePharmacyContact;
    }

    public String getMedicineLng() {
        return medicineLng;
    }

    public void setMedicineLng(String medicineLng) {
        this.medicineLng = medicineLng;
    }

    public String getMedicineLat() {
        return medicineLat;
    }

    public void setMedicineLat(String medicineLat) {
        this.medicineLat = medicineLat;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineAddress() {
        return medicineAddress;
    }

    public void setMedicineAddress(String medicineAddress) {
        this.medicineAddress = medicineAddress;
    }

    public String getMedicineCity() {
        return medicineCity;
    }

    public void setMedicineCity(String medicineCity) {
        this.medicineCity = medicineCity;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(Double.parseDouble(getMedicineLat()), Double.parseDouble(getMedicineLng()));
    }
}
