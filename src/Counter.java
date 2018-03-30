/**
 * An accumulator for a sum.
 * 
 * @author Visurt Anuttivong
 */
public class Counter {
	protected long total;

	/**
	 * Initializes a new Counter.
	 */
	public Counter() {
		total = 0;
	}

	/**
	 * Add an amount to the total.
	 * 
	 * @param amount the amount to add
	 */
	public void add(int amount) {
		total += amount;
	}

	/**
	 * Get the total value of counter.
	 */
	public long get() {
		return total;
	}
}
