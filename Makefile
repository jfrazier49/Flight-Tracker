runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar lib/junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java AlgorithmsAE.class
	javac -cp lib/junit5.jar:. AlgorithmEngineerTests.java

AlgorithmsAE.class: AlgorithmsAE.java AlgorithmEngineerInterface.class BaseGraph.class
	javac AlgorithmsAE.java

AlgorithmEngineerInterface.class: AlgorithmEngineerInterface.java
	javac AlgorithmEngineerInterface.java

BaseGraph.class: BaseGraph.java
	javac BaseGraph.java

DataWranglerInterface.class: DataWranglerInterface.java
	javac DataWranglerInterface.java

DataWranglerDW.class: DataWranglerDW.java DataWranglerInterface.class
	javac DataWranglerDW.java

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:./lib/junit5.jar DataWranglerTests.java

runDataWranglerTests: DataWranglerTests.class DataWranglerDW.class
	java -jar ./lib/junit5.jar -cp . --select-class=DataWranglerTests

runTests:
	make runAlgorithmEngineerTests
	make runDataWranglerTests
	make runBackendDeveloperTests
	make runFrontendDeveloperTests

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar lib/junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java BDAlgorithmEngineerInterface.class BDDataWranglerInterface.class BDBackend.class AlgorithmsAE.class DataWranglerDW.class
	javac -cp .:lib/junit5.jar BackendDeveloperTests.java

BDBackend.class: BDBackend.java BackendInterface.class
	javac BDBackend.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

BDAlgorithmEngineerInterface.class: BDAlgorithmEngineerInterface.java
	javac BDAlgorithmEngineerInterface.java

BDDataWranglerInterface.class: BDDataWranglerInterface.java
	javac BDDataWranglerInterface.java

runFrontendDeveloperTests: FrontendFDTests.class
	java --module-path ./lib --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c FrontendFDTests

FrontendFDTests.class: FrontendFDTests.java FrontendFD.class BackendFD.class
	javac --module-path ./lib --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontendFDTests.java

run: FrontendFD.class
	java --module-path lib/ --add-modules javafx.controls,javafx.fxml FrontendFD.java

FrontendFD.class: FrontendFD.java BDBackend.class AlgorithmsAE.class DataWranglerDW.class
	javac --module-path lib/ --add-modules javafx.controls,javafx.fxml FrontendFD.java

BackendFD.class: BackendFD.java
	javac BackendFD.java

clean:
	rm *.class
