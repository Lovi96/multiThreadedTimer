import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Lovi on 2017. 06. 02. @ 15:54.
 */
public class TimerHandler implements Runnable {
	private Map<Timer,Thread> timers = new HashMap<>();
	@Override
	public void run() {
		System.out.println("AppStart!");
		Scanner scanner = new Scanner(System.in);
		while (true) {

			if (Thread.currentThread().isInterrupted()) return;

			if (scanner.hasNext()) {
				String input = scanner.nextLine();
				inputDecider(input);
			}
		}
	}

	private void inputDecider(String input) {
		String[] splitInput = input.toLowerCase().split(" ");
		if(splitInput.length == 1 && splitInput[0].equals("check")){
			check();
		}
		else if(splitInput.length == 2){
			String function = splitInput[0];
			String timerName = splitInput[1];
			Timer timer = null;
			if(!splitInput[0].equals("create")){
				timer = getTimerByName(timerName);
				if(timer == null)return;
			}
			switch(function){
				case "check":
					check(timer);
					break;
				case "create":
					create(timerName);
					break;
				case "stop":
					stop(timer);
					break;
				case "start":
					start(timer);
					break;
				default:
					System.out.println("Bad function!");
			}


		}
	}

	private void start(Timer timer) {
		if(timer.isRunning()){
			System.out.println("Timer already started!");
			return;
		}
		Thread timerThread = new Thread(timer);
		timers.put(timer,timerThread);
		timerThread.start();
	}

	private void stop(Timer timer) {
		if(!timer.isRunning()){
			System.out.println("Timer already stopped!");
			return;
		}
		timers.get(timer).interrupt();
		timers.put(timer,null);
	}

	private void create(String timerName) {
		Timer timer = new Timer(timerName);
		timers.put(timer,null);
	}

	private Timer getTimerByName(String name) {
		for (Map.Entry<Timer, Thread> mapEntry : timers.entrySet()) {
			if (mapEntry.getKey().getName().equalsIgnoreCase(name)) return mapEntry.getKey();
		}
		System.err.println("There is no timer with this name");
		return null;
	}

	private void check() {
		for (Map.Entry<Timer, Thread> timerThreadEntry : timers.entrySet()) {
			System.out.println(timerThreadEntry.getKey() + (timerThreadEntry.getKey().isRunning() ? " id: " + timerThreadEntry.getValue().getId() : ""));
		}
	}
	private void check(Timer timer) {
		System.out.println(timer + (timer.isRunning() ? " id: " + timers.get(timer).getId() : ""));
	}
}
