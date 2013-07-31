package com.frantisekpost.util.cpukiller;

/**
 * Thread, which lets {@link ComputingUnitRunner} to easilly stop 
 * 
 * @author Frantisek Post
 *
 */
public class StopableThread extends Thread {

	private boolean working = true;

	public StopableThread(SingleComputingUnit target) {
		super(target);
	}

	void stopWorking() {
		working = false;
	}

	boolean isWorking() {
		return working;
	}

}
