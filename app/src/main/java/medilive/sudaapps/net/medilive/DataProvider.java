package medilive.sudaapps.net.medilive;

/**
 * Created by muawia.ibrahim on 10/14/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider {

    public static HashMap<String, List<String>> getInfo()
    {
        HashMap<String, List<String>> LimitsAndSignsDetails = new HashMap<String, List<String>>();

        List<String> HEMATOLOGIC = new ArrayList<String>();
        HEMATOLOGIC.add("300 Rise of an Empire");
        HEMATOLOGIC.add("Robocop");
        HEMATOLOGIC.add("The Hunger Games");
        HEMATOLOGIC.add("The Expendables 3");
        HEMATOLOGIC.add("Guardian of the Galaxy");

        List<String> Romntic_Movies = new ArrayList<String>();
        Romntic_Movies.add("Mean Girls");
        Romntic_Movies.add("Failure To Launch");
        Romntic_Movies.add("The House Bunny");
        Romntic_Movies.add("Ghost of Girlfriends Past");
        Romntic_Movies.add("The Devil Wears Prada");

        List<String> Horror_Movies= new ArrayList<String>();
        Horror_Movies.add("The Conjuring");
        Horror_Movies.add("Drag Me to Hell");
        Horror_Movies.add("Sinister");
        Horror_Movies.add("Sleepy Hollow");
        Horror_Movies.add("Eden lake");

        List<String> Comedy_Movies = new ArrayList<String>();
        Comedy_Movies.add("Ride Along");
        Comedy_Movies.add("That Awkward Moment");
        Comedy_Movies.add("Wish I Was Here");
        Comedy_Movies.add("About last Night");
        Comedy_Movies.add("This is the End");

        LimitsAndSignsDetails.put("HEMATOLOGIC", HEMATOLOGIC);
        LimitsAndSignsDetails.put("Health Conditions", Romntic_Movies);
        LimitsAndSignsDetails.put("Limits & vital signs", Horror_Movies);
        LimitsAndSignsDetails.put("Extra's", Comedy_Movies);

        return LimitsAndSignsDetails;

    }

}