
import java.io.OutputStream;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCommThread extends Thread {

	private boolean running = true;
	private SerialPort serialPort;
	private int sendInt = 0;

	public SerialCommThread(SerialPort serialPort) {
		this.serialPort = serialPort;
		start();
	}
	@Override
	public void run() {
		
		while(running) {
			//	InputStream in = serialPort.getInputStream();
			OutputStream out = serialPort.getOutputStream();
			try {
				//	if(in.available()>2) {
				//	System.out.println(in.read());
				//	}
				out.flush();
				out.write(sendInt);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(sendInt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
	}
	@Override
	public void interrupt() {
		running = false;
		this.serialPort.closePort();
		System.out.println("Serial Comm Stop");
	}
	public void Send(int sendInt) {
		this.sendInt = sendInt;
	}
}
