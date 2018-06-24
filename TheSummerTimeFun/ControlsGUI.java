import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ControlsGUI extends VBox {
	private SerialPortControl serialPortContorl;
	private BattleCommards battleCommands;
	public ControlsGUI() {
		setStyle("-fx-background-color: 'pink'");
		setAlignment(Pos.CENTER);
		
		SerialComm serialComm = new SerialComm();
		serialPortContorl = new SerialPortControl(serialComm); 
		battleCommands = new BattleCommards(serialComm);
		
		
		getChildren().addAll(serialPortContorl,battleCommands);
	}
	public void stopThreads() {
		serialPortContorl.stopThread();
		battleCommands.stopThread();
	}
	
}
