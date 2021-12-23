package diningphilosophers;

public class Philosopher extends Thread {

	private final int id;
	
	private final Fork leftFork;
	private final Fork rightFork;
	
	public Philosopher(int id, Fork left, Fork right) {
		this.id = id;
		leftFork = left;
		rightFork = right;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	private int getPhilId() {
		return id;
	}
	
	@Override
	public boolean equals(Object phil) {
		if (phil instanceof Philosopher) {
			return ((Philosopher) phil).getPhilId() == this.getPhilId();
		}
		else {
			return false;
		}
	}

}
