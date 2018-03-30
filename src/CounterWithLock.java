import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A subclass of Counter using ReentrantLock.
 * 
 * @author Visurt Anuttivong
 */
public class CounterWithLock extends Counter {
	private Lock lock = new ReentrantLock();

	/**
	 * Locked before adding an amount to the total then unlock it.
	 * 
	 * @param amount the amount to add
	 */
	public void add(int amount) {
		try {
			lock.lock();
			super.add(amount);
		} finally {
			lock.unlock();
		}
	}
}