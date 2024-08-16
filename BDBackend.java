
import java.io.FileNotFoundException;
import java.util.List;

public class BDBackend implements BackendInterface {

    DataWranglerInterface dataWrangler; 
    AlgorithmEngineerInterface<String, Double> dijkstraGraph; 


    public BDBackend(DataWranglerInterface dw, AlgorithmEngineerInterface<String, Double> ae) {
        dataWrangler = dw;
        dijkstraGraph = ae;
    }

    /**
     * Loads all DOT flight paths between airports, along with their prices and
     * distances. Not implemented as intended because there is no way for BD to
     * access which edges correlate to which edges.
     * 
     */
    @Override
    public void loadFlights() throws FileNotFoundException {

        // Load all airports and their corresponding prices and distances
        try {
            dataWrangler.loadAirports("airports.txt");
            dataWrangler.loadFlightPaths("cost.dot", "distance.dot");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File path does not exist!"); 
        } 

        // get the lists of airports and flight paths
        List<String> airports = dataWrangler.getAirportList();
        List<FlightPath> flightPaths = dataWrangler.getFlightPaths();

        // add all the airport nodes to the graph
        for (int i = 0; i < airports.size(); i++) {
            dijkstraGraph.insertNode(airports.get(i));
        }

        // add the edges for price and distance to the graph
        for (FlightPath fp : flightPaths) {
            this.dijkstraGraph.insertEdge(fp.startDest, fp.endDest, (double) fp.distance, fp.cost);
        }

    }

    /**
     * Lists all available airports for the frontend to choose from (using data
     * wrangler interface)
     * 
     * @return a String array of all the airports
     */
    @Override
    public String[] listAirports() {

        List<String> airports = dataWrangler.getAirportList(); // list of airports
        String[] toReturn = new String[airports.size()]; 

        for (int i = 0; i < airports.size(); i++) {
            toReturn[i] = airports.get(i); 
        }

        return toReturn; 
    }

    public String[] shortestPath(String src, String dest, boolean isCheapest) {
        // lists of airports traversed by Dijkstra's algorithm
        List<String> shortestPathGraph = dijkstraGraph.shortestPathData(src, dest, isCheapest);
        String[] toReturn = new String[shortestPathGraph.size()]; // array to return

        // converting list of array
        for (int i = 0; i < shortestPathGraph.size(); i++) {
            toReturn[i] = shortestPathGraph.get(i);
        }

        // return the array
        return toReturn;

    }

    public static void main(String[] args) {
        AlgorithmsAE<String, Double> dijkstraGraph = new AlgorithmsAE<>(); 
        DataWranglerDW dw = new DataWranglerDW(); 
        BDBackend backend = new BDBackend(dw, dijkstraGraph); 

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("expetw");
        }

        String[] airports = backend.listAirports();

        if (airports.length == 24) {
            System.out.println("good");
        }

        System.out.println(airports.length);
        for (int i = 0; i < airports.length; i++) {
            System.out.println(airports[i]);
        }
        
    }

}
