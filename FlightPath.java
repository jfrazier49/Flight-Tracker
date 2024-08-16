
public class FlightPath {
    public String startDest; // start of the flight path 
    public String endDest; // end of the flight path 
    public double cost; // cost of the flight 
    public int distance; // distance traveled during the flight 

    public FlightPath(String startDest, String endDest, double cost, int distance) {
        this.startDest = startDest; 
        this.endDest = endDest; 
        this.cost = cost; 
        this.distance = distance; 
    }

    /**
     * Overridden toString() method 
     */
    @Override
    public String toString() {
      return startDest + ", " + endDest + ", " + cost + ", " + distance;

    }
    }
    
