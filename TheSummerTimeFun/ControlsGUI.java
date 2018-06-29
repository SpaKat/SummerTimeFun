import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class ControlsGUI extends BorderPane {
	private SerialPortControl serialPortContorl;
	private BattleCommards battleCommands;
	public ControlsGUI() {
		setStyle("-fx-background-color: 'pink'");
	//	setAlignment(currentlyProcessedChild, Pos.CENTER);
		
		SerialComm serialComm = new SerialComm();
		serialPortContorl = new SerialPortControl(serialComm); 
		battleCommands = new BattleCommards(serialComm);
		
	setBottom(	serialPortContorl);
	setCenter(battleCommands);
		
	}
	public void stopThreads() {
		serialPortContorl.stopThread();
		battleCommands.stopThread();
	}
	
}
