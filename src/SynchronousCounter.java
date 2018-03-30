/**
 * A subclass of Counter using Synchronized.
 * 
 * @author Visurt Anuttivong
 */
public class SynchronousCounter extends Counter {

	/**
	 * Add an amount to the total.
	 * 
	 * @param amount the amount to add
	 */
	public synchronized void add(int amount) {
		super.total += amount;
	}
}