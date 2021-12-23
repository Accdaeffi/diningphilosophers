package diningphilosophers;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello world!");
		
		Fork firstFork = new Fork();
		Fork secondFork = new Fork();
		Fork thirdFork = new Fork();
		Fork fourthFork = new Fork();
		Fork fifthFork = new Fork();
		
		Philosopher firstPhil = new Philosopher(0, firstFork, fifthFork);
		Philosopher secondPhil =  new Philosopher(1, secondFork, firstFork);
		Philosopher thirdPhil = new Philosopher(2, thirdFork, secondFork);
		Philosopher fourthPhil =  new Philosopher(3, fourthFork, thirdFork);
		Philosopher fifthPhil =  new Philosopher(4, fifthFork, fourthFork);
		
		firstPhil.start();
		secondPhil.start();
		thirdPhil.start();
		fourthPhil.start();
		fifthPhil.start();
		
	}

}
