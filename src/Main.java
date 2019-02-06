/**
 * Created by Mike on 2/6/19.
 * Author: Cameron Knight 
 * Email: cjk1144@rit.edu
 */

import java.util.ArrayList;
import java.util.List;

public class Main{
	
	public static void main(String[] args){
		int helpTime = 6000;
		int cycles = 5;
		int numStudents = 6;
		int codintTime = 3000;
		int gamingTime = 3000;
		Line line = new Line(helpTime, cycles, numStudents);
		List<Thread> threads = new ArrayList<Thread>();
		TA ta = new TA(line, helpTime, gamingTime);
		Thread threadier = new Thread(ta);
		threadier.start();
		threads.add(threadier);
		for(int i = 0; i < numStudents; i++){
			Student student = new Student(line, cycles,codintTime, "Student " + i);
			Thread thready = new Thread(student);
			threads.add(thready);
			thready.start();
		}

		for (Thread thready : threads){
			try {
				thready.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		
	}
	
}
