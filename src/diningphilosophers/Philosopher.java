package diningphilosophers;

public class Philosopher extends Thread {
	
	private static final int EAT_COUNT = 40;
	private static final int THINK_TIME = 1000; 
	private static final int EAT_TIME = 100;
	
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
		try {
			sleep((long) (Math.random()*THINK_TIME));
			for (int i=0; i<EAT_COUNT; i++) {
				wantToEat();
				sleep((long) (THINK_TIME/2 + Math.random()*THINK_TIME/2));
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	private void wantToEat() throws InterruptedException {
		final int ATTEMPTS_TO_EAT = 100;
		boolean canEat = false;
		
		for (int i=0; i<ATTEMPTS_TO_EAT; i++) {
			synchronized(Philosopher.class) {
				if (leftFork.tryToTake(this) && rightFork.tryToTake(this)) {
					canEat = true;
					break;
				} else {
					leftFork.tryToPut(this);
					rightFork.tryToPut(this);
				}
			}
			sleep(EAT_TIME/ATTEMPTS_TO_EAT);
		}
		
		if (canEat) {
			sleep((long) (Math.random()*EAT_TIME));
			
			System.out.println(String.format("Philosopher %d is happy!", this.getPhilId()));
			
			leftFork.tryToPut(this);
			rightFork.tryToPut(this);
		}
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
