package com.frantisekpost.util.cpukiller;


/**
 * Simple console implementation, which takes argument with value of CPU load.
 *  
 * @author Frantisek Post
 *
 */
public class CpuKiller {

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 1) {
			System.out.println("argument with CPU load (inside the interval (0.1,0.99)) needed\n");
			return;
		}
		try {
			float load = Float.parseFloat(args[0]);
			if (load < 0.1) {
				System.out.println("load must be greater or equal to 0.1 - 10%");
				return;
			} else if (load > 0.99) {
				System.out.println("load must be lesser or equal to 0.99 - 99%");
				return;
			}
			
			new CpuKiller().run(load);
		}
		catch (NumberFormatException nfe) {
			System.out.println("wrong load argument value (" + args[0] + ")");
		}
	}
	
	void run(float load) {
		ComputingUnitRunner cuRunner = new ComputingUnitRunner(load);
		cuRunner.run();
	}
	
}
