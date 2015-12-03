package medilive.sudaapps.net.medilive.model;

/**
 * Created by muawia.ibrahim on 11/11/2015.
 */
public class SuggestGetSet {

    String medi_id,med_name;
    public SuggestGetSet(String medi_id, String med_name) {
        this.setMedi_id(medi_id);
        this.setMed_name(med_name);
    }

    public String getMedi_id() {
        return medi_id;
    }

    public void setMedi_id(String medi_id) {
        this.medi_id = medi_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }
}