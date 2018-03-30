/**
 * SubtractTask subtracts number 1 ... limit to the counter, then exits.
 * 
 * @author Visurt Anuttivong
 */
public class SubtractTask implements Runnable {
	private Counter counter;
	private int limit;

	/**
	 * Initializes a new SubtractTask.
	 * 
	 * @param counter the counter time
	 * @param limit the limit number of subtracted number
	 */
	public SubtractTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	/**
	 * Run the task by subtracting to counter.
	 */
	public void run() {
		for (int k = 1; k <= limit; k++)
			counter.add(-k);
	}

}
