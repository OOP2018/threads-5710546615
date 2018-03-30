import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * An application class for threads.
 * 
 * @author Visurt Anuttivoung
 */
public class ThreadSum {
	/**
	 * A method for running the program.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// upper limit of numbers to add/subtract to Counter
		final int LIMIT = 10000000;
		// number of threads
		int nthread = 5;
		// The counter that accumulates a total.
		Counter counter = new Counter();
		// Counter counter = new CounterWithLock();
		// Counter counter = new SynchronousCounter();
		// Counter counter = new AtomicCounter();

		runThreads(nthread, counter, LIMIT);
	}

	/**
	 * Running the tasks and print time used.
	 * 
	 * @param nthread the no of each thread to run
	 * @param counter the counter of tasks
	 * @param limit the limit number of added number
	 */
	public static void runThreads(int nthread, Counter counter, final int limit) {
		ExecutorService executor = Executors.newFixedThreadPool(2 * nthread);
		// start the tasks
		System.out.println("Starting threads");
		long startTime = System.nanoTime();
		for (int k = 1; k <= nthread; k++) {
			Runnable addtask = new AddTask(counter, limit);
			Runnable subtask = new SubtractTask(counter, limit);
			executor.submit(addtask);
			executor.submit(subtask);
		}
		// wait for threads to finish
		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		// print elapsed time and counter value
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		System.out.printf("Counter total is %d\n", counter.get());
	}
}
