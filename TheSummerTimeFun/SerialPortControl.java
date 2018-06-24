import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SerialPortControl extends VBox {

	private SerialComm serialComm;

	public SerialPortControl(SerialComm serialComm) {
		//------------------------------------------------------------------------------------------//
		Text askComport = new Text("Enter Comport");
		askComport.setFont(new Font(20));

		TextField enterComport = new TextField();

		HBox askComprtHbox = new HBox(50);
		askComprtHbox.setAlignment(Pos.BASELINE_LEFT);
		askComprtHbox.getChildren().addAll(askComport,enterComport);
		//------------------------------------------------------------------------------------------//
		ComboBox<String> allComports = new ComboBox<String>();
		Button findPorts = new Button("Find Comports");

		HBox allComportsHbox = new HBox(20);
		allComportsHbox.setAlignment(Pos.CENTER);
		allComportsHbox.getChildren().addAll(allComports,findPorts);
		//------------------------------------------------------------------------------------------//
		Button connect = new Button("Connect");
		Button disConnect = new Button("Disconnect");
		disConnect.setDisable(true);
		HBox connection = new HBox(20);
		connection.setAlignment(Pos.CENTER);
		connection.getChildren().addAll(connect,disConnect);
		//------------------------------------------------------------------------------------------//
		this.serialComm = serialComm;
		setSpacing(20);
		setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");
		setAlignment(Pos.CENTER);
		getChildren().addAll(  askComprtHbox , allComportsHbox  , connection);
		//------------------------------------------------------------------------------------------//
		allComports.setOnAction( e->{
			enterComport.setText(allComports.getValue());
		});
		findPorts.setOnAction(e ->{
			allComports.getItems().clear();
			allComports.getItems().addAll(serialComm.getPorts());
		});
		connect.setOnAction( e ->{
			try {
				serialComm.connect(enterComport.getText());
			} catch (Exception ohno) {
				ohno.printStackTrace();
			}
			connect.setDisable(true);
			disConnect.setDisable(false);
		});
		disConnect.setOnAction(e ->{
			try {
				serialComm.disconnect();
			}catch (Exception ohno) {
				ohno.printStackTrace();
			}
			connect.setDisable(false);
			disConnect.setDisable(true);
		});
	}

	public void stopThread() {
		try {
		serialComm.disconnect();
		}catch (NullPointerException e) {
		}
	}

}
