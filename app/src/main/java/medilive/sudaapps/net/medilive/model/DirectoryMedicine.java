package medilive.sudaapps.net.medilive.model;

/**
 * Created by Adil on 30/12/2015.
 */
public class DirectoryMedicine {

    String medicineID;
    String medicineName;
    String medicineAddress;
    String medicineCity;
    String medicineLng;
    String medicineLat;

    public DirectoryMedicine() {
    }

    public DirectoryMedicine(String medicineID, String medicineName, String medicineAddress, String medicineCity) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.medicineAddress = medicineAddress;
        this.medicineCity = medicineCity;
    }

    public DirectoryMedicine(String medicineID, String medicineName, String medicineAddress, String medicineCity, String medicineLng, String medicineLat) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.medicineAddress = medicineAddress;
        this.medicineCity = medicineCity;
        this.medicineLng = medicineLng;
        this.medicineLat = medicineLat;
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
}
