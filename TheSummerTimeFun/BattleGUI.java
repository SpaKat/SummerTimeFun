import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BattleGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainBorderPane = new BorderPane();
		ControlsGUI controls = new ControlsGUI();
		mainBorderPane.setCenter(controls);
		Scene scene = new Scene(mainBorderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Battle Board Controller");
		primaryStage.show();
		primaryStage.setOnCloseRequest(e ->{
			controls.stopThreads();
			Platform.exit();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
