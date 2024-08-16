

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.io.FileNotFoundException;
import java.util.List;

/**

 * This class represents the frontend of the Flight Tracker application, which allows the user to search for

 * flights between different airports based on either the cheapest or shortest path. The class implements the

 * FrontendInterfaceFD interface and extends the Application class in order to provide a graphical user interface.

 * The class uses the FlightBackend class that implements the BackendInterface interface to retrieve and display

 * information about the flights.

 */

public class FrontendFD extends Application implements FrontendInterfaceFD {

	protected String startAirportName;
	protected String endAirportName;
	protected boolean isCheapestSearch;
	protected BackendInterface backend;

	/**

	 * Starts the application and initializes the user interface by creating

	 *  and configuring various UI elements and adding event handlers to handle user input.

	 * @param stage The primary stage of the application which serves as the container for

	 *  all UI elements.

	 */

	@Override

	public void start(final Stage stage) {

		backend = new BDBackend(new DataWranglerDW(), new AlgorithmsAE<String, Double>());

		try {
			backend.loadFlights();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 



		BorderPane borderPane = new BorderPane();

		Scene scene = new Scene(borderPane, 1280, 960);

		stage.setTitle("Flight Tracker"); 



		Pane topPane = new Pane();

		borderPane.setTop(topPane);



		HBox topBox = new HBox(20);

		VBox startBox = new VBox();

		VBox endBox = new VBox();



		topPane.getChildren().add(topBox);

		topBox.getChildren().add(startBox);

		topBox.getChildren().add(endBox);



		Label startLabel = new Label("Starting Airport:");

		Label endLabel = new Label("Ending Airport:");

		startBox.getChildren().add(startLabel);

		endBox.getChildren().add(endLabel); 



		Button exitButton = new Button("Exit");

		exitButton.setOnAction(event ->

			Platform.exit());

		borderPane.setBottom(exitButton);

		borderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT); 

		String[] airports = new String[0];

		try {
			airports = backend.listAirports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String airport : airports) {

			final String airportName = airport;

			Button airportButton = new Button(airport);

			airportButton.setOnAction(event -> {

				this.startAirportName = airportName;

			});	

			startBox.getChildren().add(airportButton); 



			Button endAirportButton = new Button(airport);

			endAirportButton.setOnAction(event -> {

				this.endAirportName = airportName;

			});

			endBox.getChildren().add(endAirportButton); 

		}



		VBox resultsBox = new VBox(10);

		borderPane.setCenter(resultsBox);

		BorderPane.setAlignment(resultsBox, Pos.CENTER);  



		VBox searchButtonBox = new VBox(30);

		borderPane.setLeft(searchButtonBox); 



		Button searchCostButton = new Button("Search for cheapest path");	

		searchCostButton.setOnAction(event -> {

			this.isCheapestSearch = true;

			resultsBox.getChildren().add(this.search());

		});

		searchButtonBox.getChildren().add(searchCostButton);



		Button searchShortButton = new Button("Search for shortest path");

		searchShortButton.setOnAction(event -> {

			this.isCheapestSearch = false;

			resultsBox.getChildren().add(this.search()); 

		});

		searchButtonBox.getChildren().add(searchShortButton);

		stage.setScene(scene);

		stage.show();
	}

	

	/**

	 * Searches for flights based on the selected starting and ending airports, and whether the 

	 * search is for the cheapest or shortest path.

	 * Returns a Node object containing the search results to be displayed in the user interface.

	 * @param backend the backend interface object that provides access to the flight data

	 * @return a String object containing the search results to be displayed in the user interface

	 */

	@Override

	public Label search() {

		String[] airportsInOrder = backend.shortestPath(this.startAirportName, this.endAirportName, this.isCheapestSearch);
		String airportText = "";

		for (int i = 0; i < airportsInOrder.length; i++) {
			if (i > 0) {
				airportText += "  -->  ";
			}
			airportText += airportsInOrder[i]; 
		}
		
		Label routeLabel = new Label(airportText);
		return routeLabel; 
	}
	

	public static void main(String[] args) {
		Application.launch();
	}



	@Override
	public void startApp() {
		return;
	}

}

