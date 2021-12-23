package diningphilosophers;

public class Fork {
	
	private boolean isUsed;
	private Philosopher usedBy;

	public Fork() {
		isUsed = false;
		usedBy = null;
	}
	
	/**
	 * Tries to take a fork. 
	 * 
	 * @param phil philosopher, which try to take a fork
	 * @return true, if successfully, false otherwise
	 */
	public boolean tryToTake(Philosopher phil) {
		boolean result = false;
		
		if (!isUsed) {
			isUsed = true;
			usedBy = phil;
			result = true;
		} else {		
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Tries to put a fork.
	 * 
	 * @param phil philosopher, which try to put a fork
	 * @return true, if successfully, false otherwise
	 */
	public boolean tryToPut(Philosopher phil) {
		boolean result = false;
		
		if (isUsed && usedBy.equals(phil)) {
			isUsed = false;
			usedBy = null;
			result = true;
		} else {		
			result = false;
		}
		
		return result;
	}

}
