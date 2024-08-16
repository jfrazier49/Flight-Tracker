
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

public class AlgorithmEngineerTests {
    /**
     * Uses graph discussed in lecture to test whether shortest path is computed
     * correctly
     */
    @Test
    public void test1() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

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

        graph.insertEdge("A", "H", 8, 1);
        graph.insertEdge("A", "B", 1, 2);
        graph.insertEdge("A", "M", 5, 3);
        graph.insertEdge("B", "M", 3, 4);
        graph.insertEdge("D", "A", 7, 5);
        graph.insertEdge("D", "G", 2, 6);
        graph.insertEdge("F", "G", 9, 7);
        graph.insertEdge("G", "L", 7, 8);
        graph.insertEdge("H", "B", 6, 9);
        graph.insertEdge("H", "I", 2, 10);
        graph.insertEdge("I", "H", 2, 11);
        graph.insertEdge("I", "D", 1, 12);
        graph.insertEdge("I", "L", 5, 13);
        graph.insertEdge("M", "E", 3, 14);
        graph.insertEdge("M", "F", 4, 15);

        List<String> path = graph.shortestPathData("D", "I", false);
        assertEquals("D", path.get(0));
        assertEquals("A", path.get(1));
        assertEquals("H", path.get(2));
        assertEquals("I", path.get(3));

        double cost = graph.shortestPathCost("D", "I", false);
        assertEquals(17, cost);
    }

    /**
     * Tests the same graph with different starting and end points than discussed in
     * lecture
     */
    @Test
    public void test2() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

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

        graph.insertEdge("A", "H", 8, 1);
        graph.insertEdge("A", "B", 1, 2);
        graph.insertEdge("A", "M", 5, 3);
        graph.insertEdge("B", "M", 3, 4);
        graph.insertEdge("D", "A", 7, 5);
        graph.insertEdge("D", "G", 2, 6);
        graph.insertEdge("F", "G", 9, 7);
        graph.insertEdge("G", "L", 7, 8);
        graph.insertEdge("H", "B", 6, 9);
        graph.insertEdge("H", "I", 2, 10);
        graph.insertEdge("I", "H", 2, 11);
        graph.insertEdge("I", "D", 1, 12);
        graph.insertEdge("I", "L", 5, 13);
        graph.insertEdge("M", "E", 3, 14);
        graph.insertEdge("M", "F", 4, 15);

        List<String> path = graph.shortestPathData("I", "M", false);
        assertEquals("I", path.get(0));
        assertEquals("H", path.get(1));
        assertEquals("B", path.get(2));
        assertEquals("M", path.get(3));

        double cost = graph.shortestPathCost("I", "M", false);
        assertEquals(11, cost);
    }

    /**
     * Tests if shortestPathData and shortestPathCost correctly throw exceptions if
     * path from start to end does not exist
     */
    @Test
    public void test3() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

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

        graph.insertEdge("A", "H", 8, 1);
        graph.insertEdge("A", "B", 1, 2);
        graph.insertEdge("A", "M", 5, 3);
        graph.insertEdge("B", "M", 3, 4);
        graph.insertEdge("D", "A", 7, 5);
        graph.insertEdge("D", "G", 2, 6);
        graph.insertEdge("F", "G", 9, 7);
        graph.insertEdge("G", "L", 7, 8);
        graph.insertEdge("H", "B", 6, 9);
        graph.insertEdge("H", "I", 2, 10);
        graph.insertEdge("I", "H", 2, 11);
        graph.insertEdge("I", "D", 1, 12);
        graph.insertEdge("I", "L", 5, 13);
        graph.insertEdge("M", "E", 3, 14);
        graph.insertEdge("M", "F", 4, 15);

        assertThrows(NoSuchElementException.class, () -> graph.shortestPathCost("B", "A", false));
        assertThrows(NoSuchElementException.class, () -> graph.shortestPathData("B", "A", false));
    }

    /**
     * tests if computeShortestPath still works with parameter cost instead of distance
     */
    @Test
    public void test4() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

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

        List<String> path = graph.shortestPathData("D", "I", true);
        assertEquals("D", path.get(0));
        assertEquals("A", path.get(1));
        assertEquals("H", path.get(2));
        assertEquals("I", path.get(3));

        double cost = graph.shortestPathCost("D", "I", true);
        assertEquals(17, cost);
    }

    /**
     * Tests getEdgeCount, getNodeCount, containsEdge, and containsNode methods
     */
    @Test
    public void test5() {
        AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");

        graph.insertEdge("A", "B", 2, 1);
        graph.insertEdge("D", "A", 5, 7);
        graph.insertEdge("B", "D", 6, 3);

        assertEquals(graph.getEdgeCount(), 3);
        assertEquals(graph.getNodeCount(), 3);

        assertTrue(graph.containsEdge("A", "B"));
        assertFalse(graph.containsEdge("A", "D"));

        assertTrue(graph.containsNode("A"));
        assertFalse(graph.containsNode("C"));
    }

    /*
     * Tests backend loadFlight method
     */
    @Test
    public void codeReviewOfBackendDeveloper1() {
        AlgorithmsAE<String, Double> graph = new AlgorithmsAE<>();
        BDBackend backend = new BDBackend(new DataWranglerDW(), graph);

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(graph.containsEdge("Los Angeles", "San Francisco"));
        assertTrue(graph.containsEdge("Miami", "New York"));
        assertTrue(graph.containsEdge("Los Angeles", "New York"));
    }

    /*
     * Tests backend listAirports method
     */
    @Test
    public void codeReviewOfBackendDeveloper2() {
        AlgorithmsAE<String, Double> graph = new AlgorithmsAE<>();
        BDBackend backend = new BDBackend(new DataWranglerDW(), graph);

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] airports = backend.listAirports();

        assertTrue(airports[0].equals("Los Angeles"));
        assertTrue(airports[1].equals("San Francisco"));
        assertTrue(airports[2].equals("Miami"));
    }

    /*
     * Test if all components of backend work once integrated
     */
    @Test
    public void integrationTest1() {
        AlgorithmsAE<String, Double> graph = new AlgorithmsAE<>();
        BDBackend backend = new BDBackend(new DataWranglerDW(), graph);

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(graph.containsEdge("Los Angeles", "San Francisco"));
        assertTrue(graph.containsEdge("Miami", "New York"));
        assertTrue(graph.containsEdge("Los Angeles", "New York"));
        String[] airports = backend.listAirports();
        assertTrue(airports[0].equals("Los Angeles"));
        assertTrue(airports[1].equals("San Francisco"));
        assertTrue(airports[2].equals("Miami"));
    }

    @Test
    public void IntegrationTest2() {
        AlgorithmsAE<String, Double> graph = new AlgorithmsAE<>();
        BDBackend backend = new BDBackend(new DataWranglerDW(), graph);

        try {
            backend.loadFlights();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] flightCostPath = backend.shortestPath("Charlotte", "West Palm Beach", false);

        assertEquals("Charlotte", flightCostPath[0]);
        assertEquals("New York", flightCostPath[1]);
        assertEquals("West Palm Beach", flightCostPath[2]);
    }
}
