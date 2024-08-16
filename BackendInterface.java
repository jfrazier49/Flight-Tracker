
import java.io.FileNotFoundException;
import java.util.List;

public interface BackendInterface {
    public void loadFlights() throws FileNotFoundException; // loads a DOT flight paths between airports

    public String[] listAirports() throws FileNotFoundException; // lists all available airports for the frontend to choose from (using data
                                    // wrangler interface)

    public String[] shortestPath(String src, String dest, boolean isCheapest); // Finds the shortest/cheapest path
                                                                               // between two given airports using AE’s
                                                                               // implementation
}
