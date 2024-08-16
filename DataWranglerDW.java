// --== CS400 File Header Information ==--
// Name: Joseph Frazier
// Email: jfrazier5@wisc.edu
// Group and Team: AH, blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides data for the flight tracker project
 * 
 * @author josephfrazier
 *
 */
public class DataWranglerDW implements DataWranglerInterface {

  /**
   * Object which contains information about the one individual flight
   * 
   * @author josephfrazier
   *
   */
  
  protected ArrayList<FlightPath> flightPaths;

  /**
   * This constructor initializes the airports
   */
  public DataWranglerDW() {
    this.flightPaths = new ArrayList<FlightPath>();
    this.airports = new ArrayList<String>();
    this.costs = new ArrayList<String>();
    this.distances = new ArrayList<String>();

  }

  // this is the data field which lists all the airports in this dijkstra project
  protected ArrayList<String> airports;

  protected ArrayList<String> costs; // data field that provides the
  // costs between 2 airports

  protected ArrayList<String> distances; // data field that provides the
  // distances between 2 airports

  /**
   * This method returns all the airports in the project
   * 
   * @return all the airports in the graph
   */
  public List<String> getAirportList() {
    return this.airports;
  }

  /**
   * This method returns all the costs between 2 airports that is directly connected to each other
   * 
   * @return all the costs between airports in the graph
   */
  public List<String> getFlightCosts() {
    return this.costs;
  }

  /**
   * This method returns all the distances between 2 airports that are directly connected to each
   * other
   * 
   * @return all the costs between airports in the graph
   */
  public List<String> getFlightDistances() {
    return this.distances;
  }

  /**
   * This method returns the list of FlightPath objects
   * @return the list of all the FlightPath objects
   */
  public List<FlightPath> getFlightPaths() {
    return this.flightPaths;
    
  }
  
  /**
   * This method loads all the possible airports from the airport txt file which lists all the
   * airports used in the dijkstra graph
   * 
   * @param filename, the name of the file used
   * @throws FileNotFoundException
   */
  public void loadAirports(String filename) throws FileNotFoundException {

    Scanner scnr = new Scanner(new File(filename));

    while (scnr.hasNextLine()) {
      String next = scnr.nextLine();
      airports.add(next);

    }
    scnr.close();

  }

  /**
   * This method loads the start destination, end destination, cost, and distance of the specific
   * path from the dot file and adds them to the flightPath object
   * 
   * @param dotfile1: cost.dot file
   * @param dotfile2: distance.dot file
   * @throws FileNotFoundException if file is invalid
   */
  public void loadFlightPaths(String costdot, String distancedot) throws FileNotFoundException {

    if (costdot.equals("distancedot") || distancedot.equals("cost.dot")) {
      throw new FileNotFoundException("dot files are inputed the other way around");
    }
    
    Scanner costscnr = new Scanner(new File(costdot));
    Scanner distancescnr = new Scanner(new File(distancedot));

    String nextCost = costscnr.nextLine();
    String nextDist = distancescnr.nextLine();

    while (costscnr.hasNextLine() && distancescnr.hasNextLine()) {

      nextCost = costscnr.nextLine();
      nextDist = distancescnr.nextLine();

      if (nextCost.startsWith("}") && nextDist.startsWith("}")) {
        break;
      }
      String[] splitted = nextCost.split(" -> ");
      String newString =
          splitted[0].replace("/", " ") + " to " + splitted[1].replaceAll("[\\[\\]]", " ");
      String[] resplit = newString.split(" to ");
   
      // to get start destination, end destination, cost, and distance
      String startDestination = resplit[0];
      String[] reresplitcost = resplit[1].split(" cost = ");
      String endDestination = reresplitcost[0];
      String strcost = reresplitcost[1].substring(1, reresplitcost[1].length() - 2);
      double cost = Double.parseDouble(strcost);
      String[] distSplit = nextDist.split(" = ");
      String strdist = distSplit[1].substring(1, distSplit[1].length() - 2);
      int distance = Integer.parseInt(strdist);

      // make a new object each iteration of while loop and add 
      // start destination, end destination, cost, and distance of each flightPath
      FlightPath flightPath = new FlightPath(startDestination, endDestination, cost, distance);
      flightPaths.add(flightPath);
    }
    costscnr.close();
    distancescnr.close();


  }


  /**
   * This method loads all the costs between 2 nodes (airports) that fly right to each other. It
   * adds all the costs to the costs data field
   * 
   * @param dotfile1 (the dot file that provides the data between 2 airports and the cost between
   *                 them)
   * @throws FileNotFoundException if file does not exist
   */
  public void loadFlightCosts(String dotfile1) throws FileNotFoundException {

    Scanner scnr = new Scanner(new File(dotfile1));

    String next = scnr.nextLine();
    while (scnr.hasNextLine()) {

      next = scnr.nextLine();

      if (next.startsWith("}")) {
        break;
      }

      String[] splitted = next.split(" -> ");

      String newString =
          splitted[0].replace("/", " ") + " to " + splitted[1].replaceAll("[\\[\\]]", " ");

      costs.add(newString);


    }
    scnr.close();


  }

  /**
   * This method loads all the distances between 2 nodes (airports) that fly right to each other. It
   * adds all the distances to the distances data field
   * 
   * @param dotfile2 (the dot file that provides the data between 2 airports and the distance
   *                 between them)
   * @throws FileNotFoundException if file does not exist
   */
  public void loadFlightDistances(String dotfile2) throws FileNotFoundException {

    Scanner scnr = new Scanner(new File(dotfile2));

    String next = scnr.nextLine();
    while (scnr.hasNextLine()) {


      next = scnr.nextLine();

      if (next.startsWith("}")) {
        break;
      }

      String[] splitted = next.split(" -> ");

      String newString =
          splitted[0].replace("/", " ") + " to " + splitted[1].replaceAll("[\\[\\]]", " ");

      distances.add(newString);


    }
    scnr.close();


  }

}
