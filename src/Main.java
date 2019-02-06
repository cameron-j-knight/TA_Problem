/**
 * Created by Mike on 2/6/19.
 * Author: Cameron Knight 
 * Email: cjk1144@rit.edu
 */

import java.util.ArrayList;

public class Main{
	
	public static void main(String[] args){
		int helpTime = 4000;
		int cycles = 5;
		int numStudents = 6;
		int codintTime = 1000;
		int gamingTime = 1000:
		Line line = new Line(helpTime, cycles, numStudents);
		List<Thread> threads = new ArrayList<Thread>();
		TA ta = new TA(line, helpTime, gamingTime);
		Thread thread = new Thread(ta);
		threads.add(thread);
		for(int i = 0; i < numStudents; i++){
			Student student = new Student(line, cycles, "Student " + i);
			Thread thread = new Thread(student);
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads){
			thread.join();
		}


		
	}
	
}
