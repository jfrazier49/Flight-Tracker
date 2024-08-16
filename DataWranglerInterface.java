// --== CS400 File Header Information ==--
// Name: Joseph Frazier
// Email: jfrazier5@wisc.edu
// Group and Team: AH, blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.util.List;

/**
 * These are the interfaces for the role of Data Wrangler which will be implemented
 * in DataWranglerDW
 * @author josephfrazier
 *
 */
public interface DataWranglerInterface {

  /**
   * This method loads all the possible airports from the airport txt file which lists all the
   * airports used in the dijkstra graph
   * 
   * @param filename, the name of the file used
   * @throws FileNotFoundException
   */
  public void loadAirports(String filename) throws FileNotFoundException;
  
  /**
   * This method loads all the costs between 2 nodes (airports) that fly right to each other. It
   * adds all the costs to the costs data field
   * 
   * @param dotfile1 (the dot file that provides the data between 2 airports and the cost between
   *                 them)
   * @throws FileNotFoundException if file does not exist
   */
  public void loadFlightCosts(String dotfile1) throws FileNotFoundException; 
  
  /**
   * This method loads all the distances between 2 nodes (airports) that fly right to each other. It
   * adds all the distances to the distances data field
   * 
   * @param dotfile2 (the dot file that provides the data between 2 airports and the distance
   *                 between them)
   * @throws FileNotFoundException if file does not exist
   */
  public void loadFlightDistances(String dotfile2) throws FileNotFoundException; // loads
  // the DOT file with the distance between two airports
 
  /**
   * This method loads the start destination, end destination, cost, and distance of the specific
   * path from the dot file and adds them to the flightPath object
   * 
   * @param dotfile1: cost.dot file
   * @param dotfile2: distance.dot file
   * @throws FileNotFoundException if file is invalid
   */
  public void loadFlightPaths(String costdot, String distancedot) throws FileNotFoundException;
  
  
  /**
   * This method returns all the airports in the project
   * 
   * @return list of all the airports in the graph
   */
  public List<String> getAirportList();
  
  /**
   * This method returns all the costs between 2 airports that is directly 
   * connected to each other
   * @return list of all the costs between airports in the graph
   */
  public List<String> getFlightCosts();
  
  /**
   * This method returns all the distances between 2 airports that are directly
   * connected to each other
   * @return list of all the costs between airports in the graph
   */
  public List<String> getFlightDistances();
  
 
  /**
   * This method returns the list of the FlightPath objects
   * @return list of all the FlightPath objects
   */
  public List<FlightPath> getFlightPaths();
}
