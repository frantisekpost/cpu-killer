package com.frantisekpost.util.cpukiller;

/**
 * @author Frantisek Post
 *
 */
public class ComputingUnitRunner {

	private StopableThread[] threads;

	public ComputingUnitRunner(float load) {

		int availableProcessors = Runtime.getRuntime().availableProcessors();
		if (load < 0.5f) {

			float p = 1 / load;
			int pc = (int) (availableProcessors / p) + 1;
			float l = (availableProcessors / (float) pc) * load;

			availableProcessors = pc;
			load = l;
		}

		threads = new StopableThread[availableProcessors];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new StopableThread(new SingleComputingUnit(load));
		}
	}

	public void run() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	public void stop() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].stopWorking();
		}
	}

}
