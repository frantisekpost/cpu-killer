package com.frantisekpost.util.cpukiller;

import java.util.Random;

/**
 * Worker class
 * 
 * @author Frantisek Post
 *
 */
public class SingleComputingUnit implements Runnable {

	private static final int MILI_IN_NANOS = 1000000;

	private long durationSum = 0;
	private long durationTrashold;

	public SingleComputingUnit(float load) {
		float p = load / (1 - load);
		durationTrashold = (long) (MILI_IN_NANOS * p);
	}

	/**
	 * since {@link Thread#sleep(long, int)} is inacurate, I count how much time
	 * computation needs to run, before it can sleep for one ms.
	 */
	@SuppressWarnings("unused")
	@Override
	public void run() {
		StopableThread t = (StopableThread) Thread.currentThread();
		while (t.isWorking()) {
			long startTime = System.nanoTime();

			// /// computation consuming CPU /////
			Random r = new Random();
			double d = r.nextDouble();
			double x = Math.sin(d) * d * Math.log10(d);
			// /// computation consuming CPU /////

			long endTime = System.nanoTime();

			long duration = endTime - startTime;
			durationSum += duration;

			if (durationSum > durationTrashold) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// noop
				}

				durationSum = 0;
			}

		}
	}

}
