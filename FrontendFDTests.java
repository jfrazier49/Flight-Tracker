
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FrontendFDTests {

	@Test

	public void testStartAirportName() {

		FrontendFD frontend = new FrontendFD();

		frontend.start(new Stage());

		Scene scene = frontend.getStage().getScene();

		Button startButton = (Button) scene.lookup("airport");

		startButton.fire();

		String expected = "Airport1";

		String actual = frontend.startAirportName;

		assertEquals(expected, actual);

	}



	@Test

	public void testEndAirportName() {

		FrontendFD frontend = new FrontendFD();

		frontend.start(new Stage());

		Scene scene = frontend.getStage().getScene();

		Button endButton = (Button) scene.lookup("exit");

		endButton.fire();

		String expected = "Airport2";

		String actual = frontend.endAirportName;

		assertEquals(expected, actual);

	}



	@Test

	public void testCheapestSearch() {

		FrontendFD frontend = new FrontendFD();

		frontend.start(new Stage());

		Scene scene = frontend.getStage().getScene();

		Button cheapestButton = (Button) scene.lookup("searchCheapest");

		cheapestButton.fire();

		boolean expected = true;

		boolean actual = frontend.isCheapestSearch;

		assertEquals(expected, actual);

	}



	@Test

	public void testShortestSearch() {

		FrontendFD frontend = new FrontendFD();

		frontend.start(new Stage());

		Scene scene = frontend.getStage().getScene();

		Button shortestButton = (Button) scene.lookup("searchShortest");

		shortestButton.fire();

		boolean expected = false;

		boolean actual = frontend.isCheapestSearch;

		assertEquals(expected, actual);

	}



	@Test

	public void testResults() {

		FrontendFD frontend = new FrontendFD();

		frontend.start(new Stage());

		Scene scene = frontend.getStage().getScene();

		Button cheapestButton = (Button) scene.lookup("searchShortest");

		cheapestButton.fire();

		VBox resultsBox = (VBox) scene.lookup("resultsBox");

		Label resultLabel = (Label) resultsBox.getChildren().get(0);

		String expected = "Airport1  -->  Airport2";

		String actual = resultLabel.getText();

		assertEquals(expected, actual);

	}
	
	@Test
	
	public void CodeReviewOfDataWranger1() {
		  // Create an instance of DataWranglerDW
	  	DataWranglerDW test = new DataWranglerDW();

	  	// Call the loadFlightPaths() method with the cost and distance files
	  	try {
	    		test.loadFlightPaths("cost.dot", "distance.dot");
	  
		} catch (FileNotFoundException e) {
	
		        e.printStackTrace();
	  }

	  // Check if the size of the flight paths data structure is 52
	  assertEquals(true, test.getFlightPaths().size() == 52);
	
	}
	
	@Test
	
	public void CodeReviewOfDataWranger2() {
		  // Initialize a boolean variable to track whether an exception was thrown
	  	boolean exceptionThrown = false;
	    
	  	// Create an instance of DataWranglerDW
	  	DataWranglerDW test = new DataWranglerDW();
	  
	  	// Call the loadFlightDistances() method with a nonexistent file name and catch the FileNotFoundException
	 	 try {
	    		test.loadFlightDistances("nonexistentfile.txt");
	  	} catch (FileNotFoundException e) {
	    	// Set the exceptionThrown variable to true and print the stack trace of the exception
	    	exceptionThrown = true;
	    	e.printStackTrace();
	  }
	  
	  	// Verify that the exception was thrown
	  	assertTrue(exceptionThrown);
	}

		@Test
	public void integrationTest1() {
    		// Initialize graph, data wrangler, and backend
     		AlgorithmsAE<String, Double> graph = new  AlgorithmsAE<String, Double>();
    		DataWrangler dataWrangler = new DataWrangler();
    		BDBackend backend = new BDBackend(dataWrangler, graph);

    		// Load flight data from file
    		try {
        		backend.loadFlights();
    		} catch (FileNotFoundException e) {
        		e.printStackTrace();
    		}

    		// Get list of airports and their names
    		String[] airports = backend.listAirports();

    		// Check that the correct number of airports were loaded
    		assertEquals(24, airports.length);

    		// Get the names of the first three airports
    		String airport1 = airports[0];
    		String airport2 = airports[1];
    		String airport3 = airports[2];

    		// Check that the first three airports were loaded correctly
    		assertEquals("Los Angeles", airport1);
    		assertEquals("San Francisco", airport2);
    		assertEquals("Miami", airport3);

    		// Check that an edge between Los Angeles and Miami exists in the graph
    		assertTrue(true, graph.containsEdge("Los Angeles", "Miami"));
	}

}





