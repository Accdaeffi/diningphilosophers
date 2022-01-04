package diningphilosophers;

public class Main {

	private final static int COUNT = 5;
	
	public static void main(String[] args) {		
        Fork[] forks = new Fork[COUNT];
        
        for(int i = 0; i < COUNT; i++) {
            forks[i] = new Fork();
        }
        
        for(int i = 0; i < COUNT; i++) {
            new Thread(new Philosopher(i, forks[i], forks[(i + 1) % COUNT])).start();
        }
		
	}

}
