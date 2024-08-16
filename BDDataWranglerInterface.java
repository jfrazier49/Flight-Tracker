
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BDDataWranglerInterface implements DataWranglerInterface {

    private ArrayList<String> flightsList; // List for hardcoded DW interface
    private ArrayList<String> priceList; // List for hardcoded DW interface
    private ArrayList<String> distanceList; // List for hardcoded DW interface
    private ArrayList<FlightPath> flightPaths; // ArrayList to store all FlightPath objects

    public BDDataWranglerInterface() {
        flightsList = new ArrayList<>();
        priceList = new ArrayList<>();
        distanceList = new ArrayList<>();
        flightPaths = new ArrayList<>();
    }

    // loads flights from file to ArrayList
    @Override
    public void loadAirports(String filename) throws FileNotFoundException {
        flightsList.add("Flight 1");
        flightsList.add("Flight 2");
        flightsList.add("Flight 3");
    }

    // returns all the airports available
    public List<String> getAirportList() {
        return flightsList;
    }

    // returns all the prices between each airport from the first DOT file
    public void loadFlightCosts(String dotfile1) throws FileNotFoundException {
        priceList.add("100");
        priceList.add("200");
        priceList.add("300");
    }

    // returns prices of flights, retrieved from the loadPrices method
    public List<String> getFlightCosts() {
        return priceList;
    }

    // returns all the distances between each airport from the second DOT file
    public void loadFlightDistances(String dotfile2) throws FileNotFoundException {
        distanceList.add("10");
        distanceList.add("20");
        distanceList.add("30");
    }

    // returns distances between airports, retrieved from the loadDistances method
    public List<String> getFlightDistances() {
        return distanceList;
    }

    public void loadFlightPaths(String costdot, String distancedot) {
        FlightPath fp1 = new FlightPath("Flight 1", "Flight 2", 100, 10);
        FlightPath fp2 = new FlightPath("Flight 2", "Flight 3", 200, 20);
        FlightPath fp3 = new FlightPath("Flight 3", "Flight 4", 300, 30);

        this.flightPaths.add(fp1);
        this.flightPaths.add(fp2);
        this.flightPaths.add(fp3);
    }

    public ArrayList<FlightPath> getFlightPaths() {
        return this.flightPaths;
    }

}
