import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BattleCommards extends VBox {
	private Timeline timeline = new Timeline();
	private BattleTime timer;
	private SerialComm serialComm;
	public BattleCommards(SerialComm s) {
		//------------------------------------------------------------------------------------------//
		setStyle("-fx-padding: 10;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: blue;");
		//------------------------------------------------------------------------------------------//
		Button startBattle = new Button("Start Battle");
		Button resetBattle = new Button("Reset Battle");
		resetBattle.setDisable(true);
		Text TheTime = new Text("00:00");
		TheTime.setStyle("-fx-font-family: 'Playbill';"
				+ "-fx-font-size: 500;"
				+ "-fx-fill: 'blue';"
				+ "");
		serialComm = s;
		//------------------------------------------------------------------------------------------//
		timeline.setCycleCount(Timeline.INDEFINITE);
		//------------------------------------------------------------------------------------------//
		startBattle.setOnAction(t ->{
			
			try {
				serialComm.Send(0);
				timer = new BattleTime(this.serialComm);
				KeyFrame keyframe = new KeyFrame(Duration.millis(100), e -> {
					TheTime.setText(timer.toString());
					if(!timer.isAlive()) {
						resetBattle.fire();
					}
				});
				timeline.getKeyFrames().clear();
				timeline.getKeyFrames().add(keyframe);
				timeline.play();
				startBattle.setDisable(true);
				resetBattle.setDisable(false);
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
			 
		});			
		resetBattle.setOnAction(e ->{
			timer.interrupt();
			timeline.stop();
			timeline.getKeyFrames().clear();
			TheTime.setText("00:00");
			startBattle.setDisable(false);
			resetBattle.setDisable(true);
		});
		//------------------------------------------------------------------------------------------//
		HBox battleTime = new HBox(20);
		battleTime.setAlignment(Pos.CENTER);
		battleTime.getChildren().addAll(startBattle,resetBattle);
		HBox timePane = new HBox(TheTime);
		timePane.setAlignment(Pos.CENTER);
		HBox keyPane = new HBox();
		Text ketText = new Text(/*"0 - stop \n1 - wait\n2 - Fight!"*/);
		keyPane.setAlignment(Pos.CENTER);
		keyPane.getChildren().add(ketText);
		getChildren().addAll(timePane,battleTime,keyPane);
	}
	public void stopThread() {
		try {
			timer.interrupt();
			timeline.stop();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
