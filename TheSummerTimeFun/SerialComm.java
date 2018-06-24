import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

public class SerialComm{
	
	private SerialPort serialPort;
	private SerialCommThread serialCommThread;
	private String port;
	public ArrayList<String> getPorts() { 
		ArrayList<String> ports = new ArrayList<String>();
		for (int i = 0; i < SerialPort.getCommPorts().length; i++) {
			System.out.println(SerialPort.getCommPorts()[i].getSystemPortName());
			ports.add(SerialPort.getCommPorts()[i].getSystemPortName());
		}
		return ports ;
	}

	public void connect(String port) {
		this.port = port;
		serialPort = SerialPort.getCommPort(port);
		serialPort.openPort();
		serialPort.setBaudRate(9600);
		serialCommThread = new SerialCommThread(serialPort);
	}
	
	public void disconnect() {
		serialCommThread.interrupt();
	}
	public void Send(int sendInt) {
		serialCommThread.Send(sendInt);
	}

}
