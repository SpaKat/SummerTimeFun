import java.util.Date;

public class BattleTime extends Thread{

	private boolean running = true;
	private int time = 0;
	private SerialComm serialComm;
	public BattleTime(SerialComm serialComm) {
		this.serialComm = serialComm;
		this.start();
	}

	@Override
	public void run() {
		// starting 4 seconds
		serialComm.Send(0);
		Date startintro = new Date();
		while (time <4 && running) {
			//System.out.println( this.getName() + "  " + this.getId()   );
			Date end = new Date();
			time = (int)((end.getTime() - startintro.getTime()) / 1000);
			if(time<2) {
				serialComm.Send(1);
			}else {
				serialComm.Send(2);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 2 minutes 
		Date start = new Date();
		while (time<120 && running) {
			//System.out.println( this.getName() + "  " + this.getId()   );
			Date end = new Date();
			time = (int)((end.getTime() - start.getTime()) / 1000);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void interrupt() {
		serialComm.Send(0);
		running = false;
		System.out.println("Timer Stop");
	}
	

	@Override
	public String toString() {
		return String.format("%02d:%02d",time/60,time%60);
	}

}
