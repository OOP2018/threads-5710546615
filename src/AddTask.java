/**
 * AddTask adds number 1 ... limit to the counter, then exits.
 * 
 * @author Visurt Anuttivong
 */
public class AddTask implements Runnable {
	private Counter counter;
	private int limit;

	/**
	 * Initializes a new AddTask.
	 * 
	 * @param counter the counter time
	 * @param limit the limit number of added number
	 */
	public AddTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	/**
	 * Run the task by adding to counter.
	 */
	public void run() {
		for (int k = 1; k <= limit; k++)
			counter.add(k);
	}
}