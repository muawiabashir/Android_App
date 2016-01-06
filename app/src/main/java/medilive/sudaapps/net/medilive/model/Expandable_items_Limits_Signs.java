package medilive.sudaapps.net.medilive.model;

/**
 * Created by muawia.ibrahim on 1/6/2016.
 */
public class Expandable_items_Limits_Signs  {
    private String test = "";
    private String men = "";
    private String women = "";

    public Expandable_items_Limits_Signs(String test, String men, String women) {
        this.test = test;
        this.men = men;
        this.women = women;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getWomen() {
        return women;
    }

    public void setWomen(String women) {
        this.women = women;
    }
}
