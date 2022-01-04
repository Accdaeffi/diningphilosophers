package diningphilosophers;

public class Philosopher extends Thread {
	
	private static final int EAT_COUNT = 60;
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
			
			//initial sleep - to start different processes in different time
			sleep((long) (Math.random()*THINK_TIME));
			
			//main cycle - eat, sleep, repeat
			for (int i=0; i<EAT_COUNT; i++) {
				wantToEat();
				sleep((long) (THINK_TIME/2 + Math.random()*THINK_TIME/2));
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	private void wantToEat() throws InterruptedException {
		//how many times philosopher will attempt to eat 
		final int ATTEMPTS_TO_EAT = 100;
		
		//true, if philosopher have two forks - and can start eating
		boolean canEat = false;
		
		//philosopher will try to take both forks for ATTEMPTS_TO_EAT times
		for (int i=0; i<ATTEMPTS_TO_EAT; i++) {
			
			/*
			 * main problem - we should synchronize not by Philosopher.class, but by both forks
			 * but this is harder
			*/
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
			//if we successfully take both forks
			sleep((long) (Math.random()*EAT_TIME));
			
			System.out.println(String.format("Philosopher %d is happy!", this.getPhilId()));
			
			leftFork.tryToPut(this);
			rightFork.tryToPut(this);
		} else {
			
			System.out.println(String.format("Philosopher %d is VERY angry!", this.getPhilId()));
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
