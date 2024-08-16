
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {

    // Testing loadFlights() to see if all airports were added correctly
    @Test
    public void test1() {
        BDAlgorithmEngineerInterface<String, Double> ae = new BDAlgorithmEngineerInterface<String, Double>();
        BDBackend bd = new BDBackend(new BDDataWranglerInterface(),
                ae);

        try {
            bd.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("Flight 1", ae.dataList.get(0));
        assertEquals("Flight 2", ae.dataList.get(1));
        assertEquals("Flight 3", ae.dataList.get(2));

    }

    // Testing loadFlights() to see if all edges were added properly
    @Test
    public void test2() {
        BDAlgorithmEngineerInterface<String, Double> ae = new BDAlgorithmEngineerInterface<String, Double>();
        BDBackend bd = new BDBackend(new BDDataWranglerInterface(),
                ae);

        try {
            bd.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(true, ae.containsEdge("Flight 1", "Flight 2"));
        assertEquals(true, ae.containsEdge("Flight 2", "Flight 3"));
        assertEquals(true, ae.containsEdge("Flight 3", "Flight 4"));
    }

    /**
     * lists all airports available using listAirports() 
     */
    @Test
    public void test3() {
        BDBackend bd = new BDBackend(new BDDataWranglerInterface(),
                new BDAlgorithmEngineerInterface<String, Double>());

        String[] airports = null;
        try {
            bd.loadFlights();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        airports = bd.listAirports();

        assertEquals("Flight 1", airports[0]);
        assertEquals("Flight 2", airports[1]);
        assertEquals("Flight 3", airports[2]);
    }

    /**
     * gets the shortest path based on cost
     */
    @Test
    public void test4() {
        BDBackend bd = new BDBackend(new BDDataWranglerInterface(),
                new BDAlgorithmEngineerInterface<String, Double>());

        try {
            bd.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] airports = bd.shortestPath("Flight 1", "Flight 2", true);

        assertEquals(airports[0], "Flight 1");
        assertEquals(airports[1], "Flight 2");
        assertEquals(airports[2], "Flight 3");
    }

    /**
     * gets the shortest path based on distance
     */
    @Test
    public void test5() {
        BDBackend bd = new BDBackend(new BDDataWranglerInterface(),
                new BDAlgorithmEngineerInterface<String, Double>());

        try {
            bd.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] airports = bd.shortestPath("Flight 1", "Flight 2", false);

        assertEquals(airports[0], "Flight 2");
        assertEquals(airports[1], "Flight 3");
        assertEquals(airports[2], "Flight 4");
    }

    /**
     * Tests loading data from the DOT files though Backend using Data Wrangler and Algorithm Engineer
     */
    @Test
    public void integrationTest1() {
        AlgorithmsAE<String, Double> dijkstraGraph = new AlgorithmsAE<String, Double>(); 
        DataWranglerDW dw = new DataWranglerDW(); 
        BDBackend backend = new BDBackend(dw, dijkstraGraph); 

        int x = 0;

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            x = 1;
        }

        assertEquals(0, x);
        String[] airports = backend.listAirports(); 

        // checking to see if airports and their edges were added correctly 
        assertEquals("Los Angeles", airports[0]); 
        assertEquals("San Francisco", airports[1]); 
        assertEquals("Miami", airports[2]); 
        assertEquals(true, dijkstraGraph.containsEdge("Los Angeles", "Miami")); 
        assertEquals(true, dijkstraGraph.containsEdge("Denver", "New York")); 
    }

    /**
     * Tests finding shortestPath() through Backend using Data Wrangler and Algorithm Engineer 
     */
    @Test
    public void IntegrationTest2() {
        AlgorithmsAE<String, Double> dijkstraGraph = new AlgorithmsAE<String, Double>(); 
        DataWranglerDW dw = new DataWranglerDW(); 
        BDBackend backend = new BDBackend(dw, dijkstraGraph); 

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] flightPathCost = backend.shortestPath("Denver", "Washington", false); 
        String[] flightPathDist = backend.shortestPath("Denver", "Washington", true); 

        assertEquals("Denver", flightPathCost[0]); 
        assertEquals("Los Angeles", flightPathCost[1]); 
        assertEquals("Washington", flightPathCost[2]); 
        assertEquals("Denver", flightPathDist[0]); 
        assertEquals("Los Angeles", flightPathDist[1]); 
        assertEquals("Washington", flightPathDist[2]); 
    }

    /**
     * Tests if shortestPathData() and shortestPathCost() throw exceptions as expected 
     */
    @Test
    public void CodeReviewOfAlgorithmEngineerTest1() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

        graph.insertNode("A"); 
        graph.insertNode("B"); 
        graph.insertNode("C"); 
        graph.insertNode("D"); 

        graph.insertEdge("A", "B", 3, 3); 
        graph.insertEdge("B", "C", 4, 4); 
        graph.insertEdge("C", "D", 5, 5); 

        assertThrows(NoSuchElementException.class, () -> graph.shortestPathData("B", "A", false)); 
        assertThrows(NoSuchElementException.class, () -> graph.shortestPathCost("B", "A", false)); 
    }

    /**
     * Testing if shortestPathData() and shortestPathCost() return the same values. 
     * Using graph provided in lecture, and the same path from D to I. 
     */
    @Test
    public void CodeReviewOfAlgorithmEngineerTest2() {
        // creating the graph 
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

        // inserting the nodes 
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");

        // inserting the edges 
        graph.insertEdge("A", "H", 1, 8);
        graph.insertEdge("A", "B", 2, 1);
        graph.insertEdge("A", "M", 3, 5);
        graph.insertEdge("B", "M", 4, 3);
        graph.insertEdge("D", "A", 5, 7);
        graph.insertEdge("D", "G", 6, 2);
        graph.insertEdge("F", "G", 7, 9);
        graph.insertEdge("G", "L", 8, 7);
        graph.insertEdge("H", "B", 9, 6);
        graph.insertEdge("H", "I", 10, 2);
        graph.insertEdge("I", "H", 11, 2);
        graph.insertEdge("I", "D", 12, 1);
        graph.insertEdge("I", "L", 13, 5);
        graph.insertEdge("M", "E", 14, 3);
        graph.insertEdge("M", "F", 15, 4);

        // getting the data 
        List<String> pathData = graph.shortestPathData("D", "I", true); 
        double cost = graph.shortestPathCost("D", "I", true); 

        assertEquals("D", pathData.get(0)); 
        assertEquals("A", pathData.get(1)); 
        assertEquals("H", pathData.get(2)); 
        assertEquals("I", pathData.get(3)); 
        assertEquals(17.0, cost);
    }
}

