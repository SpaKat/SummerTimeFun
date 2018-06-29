import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ControlsGUI extends BorderPane {
	private SerialPortControl serialPortContorl;
	private BattleCommards battleCommands;
	private boolean swapFlag = false;

	public ControlsGUI() {
		setStyle("-fx-background-color: 'pink'");
	//	setAlignment(currentlyProcessedChild, Pos.CENTER);
		
		SerialComm serialComm = new SerialComm();
		serialPortContorl = new SerialPortControl(serialComm); 
		battleCommands = new BattleCommards(serialComm);
		MenuBar menuBar = new MenuBar();
		Menu options = new Menu("Options");
		MenuItem swap = new MenuItem("Swap");
		
		swap.setOnAction( s ->{
			
			if (swapFlag) {
				setCenter(serialPortContorl);
			} else {
				setCenter(battleCommands);
			}
			swapFlag = !swapFlag;
			
		});
		
		setTop(menuBar);
		menuBar.getMenus().add(options);
		options.getItems().add(swap);
		setCenter(serialPortContorl);
		
	}
	public void stopThreads() {
		serialPortContorl.stopThread();
		battleCommands.stopThread();
	}
	
}
