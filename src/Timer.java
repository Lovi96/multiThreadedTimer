/**
 * Created by Lovi on 2017. 06. 02. @ 15:54.
 */
public class Timer implements Runnable {
	private double runTime;
	private String name;
	private boolean isRunning;

	public Timer(String name) {
		this.runTime = 0;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(printStatus());
		isRunning = true;
		while(isRunning){
			try {
				Thread.sleep(100);
				runTime += 0.1;
			} catch(InterruptedException e) {
				System.out.println(name + " has stopped running!");
				isRunning = false;
			}
		}
	}

	private String printStatus() {
		String statusMessage;
		if(runTime == 0){
			statusMessage = name + " has started running!";
		}
		else{
			statusMessage = name + " has resumed working from " + runTime +" seconds!";
		}
		return statusMessage;
	}

	public double getRunTime() {
		return runTime;
	}

	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	public String toString() {
		return name + " timer is running: " +isRunning+", time elapsed: "+runTime;
	}

	String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}
}
