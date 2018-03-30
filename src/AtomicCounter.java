import java.util.concurrent.atomic.AtomicLong;

/**
 * A subclass of Counter using AtomicLong.
 * 
 * @author Visurt Anuttivong
 */
public class AtomicCounter extends Counter {
	private AtomicLong total;

	/**
	 * Initializes a new AtomicCounter.
	 */
	public AtomicCounter() {
		total = new AtomicLong();
	}

	/**
	 * add amount to the total.
	 * 
	 * @param amount the amount to add
	 */
	public void add(int amount) {
		total.getAndAdd(amount);
	}

	/**
	 * return the total as a long value.
	 */
	public long get() {
		return total.get();
	}
}