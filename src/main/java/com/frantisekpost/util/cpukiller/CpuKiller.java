package com.frantisekpost.util.cpukiller;

import java.util.Random;

/**
 * Simple implementation creating 24 threads, computing all the time - taking theoretically 100% CPU
 *  
 * @author Frantisek Post
 *
 */
public class CpuKiller {

	public static void main(String[] args) {
		
		Thread[] threads = new Thread[24];
		
		for (int i = 0; i < 24; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@SuppressWarnings("unused")
				public void run() {
					while (true) {
						Random r = new Random();
						double d = r.nextDouble();
						double x = Math.sin(d) * d * Math.log10(d);
					}
				}
				
			});
		}
		
		for (int i = 0; i < 24; i++) {
			threads[i].start();
		}
	}
	
}
