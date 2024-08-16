
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the DataWranglerDW class using JUnit 5 Jupiter
 * @author josephfrazier
 *
 */
class DataWranglerTests {

  /**
   * This method tests the number of airports in the airports.txt file
   */
  @Test
  public void test1() {
    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadAirports("airports.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < test.getAirportList().size(); i++) {
      assertEquals(true, test.getAirportList().size() == 24);
    }
  }

  /**
   * This method tests if the 5th entry of the airport properly equals Orlando
   */
  @Test
  public void test2() {
    
    DataWranglerDW test = new DataWranglerDW();
    try {
      test.loadAirports("airports.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
      assertEquals(true, test.getAirportList().get(4).equals("Orlando"));
  }
  
  /**
   * This method tests if the DataWranglerDW class properly throws a FileNotFoundException
   * if it loads a non existent file in loadAirports
   */
  @Test
  public void test3() {
    
    boolean exceptionThrown = false;
    
    DataWranglerDW test = new DataWranglerDW();
    try {
      test.loadAirports("nonexistentfile.txt");
    } catch (FileNotFoundException e) {
      exceptionThrown = true;
      
      e.printStackTrace();
    }
    
    assertTrue(exceptionThrown);
  }

  /**
   * This method tests the number of entries in the cost.dot file properly equals 52
   */
  @Test
  public void test4() {
    DataWranglerDW test = new DataWranglerDW();
    
    try {
      test.loadFlightCosts("cost.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    assertEquals(true, test.getFlightCosts().size() == 52);
    
  }
  
  /**
   * This method tests if the first entry of the cost.dot file properly equals
   * Los Angeles to San Francisco cost = “129.58”
   */
  @Test
  public void test5() {
    DataWranglerDW test = new DataWranglerDW();
    
    try {
      test.loadFlightCosts("cost.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    assertEquals("Los Angeles to San Francisco cost = “129.58” ", test.getFlightCosts().get(0));
    
  }
  
  /**
   * This method tests if the DataWranglerDW class properly throws a FileNotFoundException
   * if it loads a non existent file in loadFlightCosts
   */
  @Test
  public void test6() {
    boolean exceptionThrown = false;
    
    DataWranglerDW test = new DataWranglerDW();
    try {
      test.loadFlightCosts("nonexistentfile.txt");
    } catch (FileNotFoundException e) {
      exceptionThrown = true;
      
      e.printStackTrace();
    }
    
    assertTrue(exceptionThrown);
    
  }
  
  /**
   * This method tests if the number of entries in distance.dot properly equals 52
   */
  @Test
  public void test7() {
    DataWranglerDW test = new DataWranglerDW();
    
    try {
      test.loadFlightDistances("distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    assertEquals(true, test.getFlightDistances().size() == 52);
    
  }
  
  /**
   * This method tests if the first entry of the distance.dot file properly equals
   * Los Angeles to San Francisco distance = “372”
   */
  @Test
  public void test8() {
    DataWranglerDW test = new DataWranglerDW();
    
    try {
      test.loadFlightDistances("distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    assertEquals("Los Angeles to San Francisco distance = “372” ", 
        test.getFlightDistances().get(0));
  }
  
  /**
   * This method tests if the DataWranglerDW class properly throws a FileNotFoundException
   * if it loads a non existent file in loadFlightDistances
   */
  @Test
  public void test9() {
    boolean exceptionThrown = false;
    
    DataWranglerDW test = new DataWranglerDW();
    try {
      test.loadFlightDistances("nonexistentfile.txt");
    } catch (FileNotFoundException e) {
      exceptionThrown = true;
      
      e.printStackTrace();
    }
    
    assertTrue(exceptionThrown);
  }

  /**
   * This method tests the newly adjusted method for the flightPaths method and checks if it
   * correctly added the flightPath object at index 0
   */
  @Test
  public void updatedTest10() {

    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadFlightPaths("cost.dot", "distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals(true,
        test.flightPaths.get(0).toString().equals("" + "Los Angeles, San Francisco, 129.58, 372"));
  }

  /**
   * This method tests if the newly adjusted method for the flightPaths method and checks if it
   * correctly added the flightPath object at index 4
   */
  @Test
  public void updatedTest11() {

    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadFlightPaths("cost.dot", "distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals(true,
        test.flightPaths.get(4).toString().equals("" + "Chicago, New York, 187.82, 773"));
  }

  /**
   * This method tests if the newly adjusted method for the flightPaths method and checks the
   * flightPaths array has the correct amount of flightPath objects in it
   */
  @Test
  public void updatedTest12() {

    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadFlightPaths("cost.dot", "distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals(true, test.flightPaths.size() == 52);
  }

  /**
   * This method tests when the loadFlightPaths method has 2 dot files in the wrong order in the
   * parameter
   */
  @Test
  public void updatedTest13() {
    DataWranglerDW test = new DataWranglerDW();
    boolean exception = false;

    try {
      test.loadFlightPaths("distance.dot", "cost.dot");
    } catch (FileNotFoundException e) {
      exception = true;

      e.printStackTrace();
    }

    assertTrue(exception);
  }

  /**
   * This method tests when the loadFlightPaths method has dot files loaded which are invalid
   */
  @Test
  public void updatedTest14() {
    DataWranglerDW test = new DataWranglerDW();
    boolean exception = false;

    try {
      test.loadFlightPaths("nonexistent.dot", "nonexistent.dot");
    } catch (FileNotFoundException e) {
      exception = true;

      e.printStackTrace();
    }

    assertTrue(exception);
  }


  /**
   * This method tests if the getFlightPaths contains the correct amount of 
   * FlightPath objects
   */
  @Test
  public void updatedTest15() {
    
    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadFlightPaths("cost.dot", "distance.dot");


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    assertEquals(true, test.getFlightPaths().size() == 52);
  }
    
  /**
   * Checks if getFlightPaths correctly contains the 5th FlightPath object element
   */
  @Test
  public void updatedTest16() {
    
    DataWranglerDW test = new DataWranglerDW();

    try {
      test.loadFlightPaths("cost.dot", "distance.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals(true,
        test.getFlightPaths().get(4).toString().equals("" + "Chicago, New York, 187.82, 773"));
  }

  /**
  *This method tests the integration of the AlgorithmEngineer, DataWrangler, and the BackendDeveloper, to check if the dijikstra graph properly contains the 
edge of Los Angeles and San Francisco
  *
  *
  */
  @Test
  public void integrationTest1() {
      AlgorithmsAE<String, Double> dijikstraGraph = new AlgorithmsAE<String, Double>();
      DataWranglerDW dw = new DataWranglerDW();
      BDBackend backend = new BDBackend(dw, dijikstraGraph);


      try {
	  backend.loadFlights();
      } catch (FileNotFoundException e) {
	  e.printStackTrace();
	  
      }

      String[] airports = backend.listAirports();
      
      assertEquals(true, dijikstraGraph.containsEdge("Los Angeles", "San Francisco"));
      assertEquals("Los Angeles", airports[0]);

  }
  /**
  * This method tests the shortest path using DataWrangler, AlgorithmEngineer, and Backend
  */
  @Test
  public void integrationTest2() {
      
      AlgorithmsAE<String, Double> dijikstraGraph = new AlgorithmsAE<String, Double>();
      DataWranglerDW dw = new DataWranglerDW();
      BDBackend backend = new BDBackend(dw, dijikstraGraph);


      try {
	  backend.loadFlights();
      } catch (FileNotFoundException e) {
	  e.printStackTrace();
	  
      }

      String[] flightPathCost = backend.shortestPath("Miami", "Seattle", false);

      assertEquals("Miami", flightPathCost[0]);
      assertEquals("New York", flightPathCost[1]);
      assertEquals("Seattle", flightPathCost[2]);
      
  }

  /**
   * This method ensures AE throws nosuchelementexception when path doesn't exist
   */
  @Test
  public void algorithmEngineerTest1() {
    AlgorithmsAE<String, Integer> graph = new AlgorithmsAE<>();

    graph.insertNode("A"); 
    graph.insertNode("B"); 
    graph.insertNode("E"); 
    graph.insertNode("G"); 

    graph.insertEdge("A", "B", 7, 2); 
    graph.insertEdge("B", "E", 4, 3); 
    graph.insertEdge("E", "G", 1, 55); 

    assertThrows(NoSuchElementException.class, () -> graph.shortestPathData("D", "A", false)); 
    assertThrows(NoSuchElementException.class, () -> graph.shortestPathCost("D", "A", false)); 
  }

  /**
   * This tests if the graph returns the correct shortest path
   */
  @Test
  public void algorithmEngineerTest2() {
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
