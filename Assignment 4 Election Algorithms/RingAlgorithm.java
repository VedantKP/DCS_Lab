/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9
Lab: CL-9
Assignment 4: To develop Ring algorithm for leader election.
*/

import java.io.*;
import java.util.*;

class Process {

	public Boolean state;
	public int processId;

	public Process(int processId, Boolean state) {
		this.state = state;
		this.processId = processId;
	}

	public int getProcessId() {
		return this.processId;
	}

} 

public class RingAlgorithm {

	static ArrayList<Process> processArray = new ArrayList<Process>();

	public static void holdElection() {

		Scanner sc = new Scanner(System.in);
		int processIndex;
		int noOfProcess = processArray.size();

		while(true) {

			System.out.println("\nCoordination Process index: ");
			processIndex = sc.nextInt();

			if(processArray.get(processIndex).state) {
				break;
			}

			System.out.println("\nProcess " + processArray.get(processIndex).processId + " is down...Select another process to hold election");
		}

		System.out.println("Process " + processArray.get(processIndex).processId + " held election");

		int i = processIndex;
		int j = (i+1)%noOfProcess;
		int leader = processArray.get(i).processId;

		while(!processArray.get(j).state) {
			j = (j+1)%noOfProcess;
		}

		while(j != processIndex) {
			System.out.println("\nProcess " + processArray.get(i).processId + " sent message to process " + processArray.get(j).processId);
			
			if(processArray.get(j).processId > leader) {
				leader = processArray.get(j).processId;
			}

			i = j;
			j = (i+1)%noOfProcess;
			while(!processArray.get(j).state) {
				j = (j+1)%noOfProcess;
			}

		}

		System.out.println("\nProcess " + processArray.get(i).processId + " sent message to process " + processArray.get(j).processId);
		System.out.println("\nProcess " + leader + " won the election");


	} 

	public static void main(String[] args) {

		System.out.println("\nEnter no of processes : ");

		Scanner sc = new Scanner(System.in);
		int noOfProcess = sc.nextInt();

		System.out.println("\nEnter process ids : ");

		for(int i=0;i<noOfProcess;i++) {

			int processId = sc.nextInt();

			Process process = new Process(processId,true);

			processArray.add(process);
		}

		//System.out.println("\nSorting processes based on processId ....");

		while(true) {
			System.out.println("\nEnter choice : \n1) Up process \n2) Down process \n3) Hold election \n4) Exit\nChoice : ");

			int choice = sc.nextInt();

			if(choice == 4) 
				break;

			switch(choice) {

				case 1 : 
					System.out.println("\nEnter process index : ");
					int temp = sc.nextInt();
					processArray.get(temp).state = true;
					System.out.println("\nChanged status of process " + processArray.get(temp).processId + " to active");
					break;

				case 2 : 
					System.out.println("\nEnter process index : ");
					int temp1 = sc.nextInt();
					processArray.get(temp1).state = false;
					System.out.println("\nChanged status of process " + processArray.get(temp1).processId + " to inactive");
					break;

				case 3 : 
					holdElection();
			}

		}

	}
}

/*

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 4 Election Algorithms>java RingAlgorithm

Enter no of processes :
6

Enter process ids :
1
2
3
4
5
6

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
3

Coordination Process index:
1
Process 2 held election

Process 2 sent message to process 3

Process 3 sent message to process 4

Process 4 sent message to process 5

Process 5 sent message to process 6

Process 6 sent message to process 1

Process 1 sent message to process 2

Process 6 won the election

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
2

Enter process index :
5

Changed status of process 6 to inactive

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
3

Coordination Process index:
1
Process 2 held election

Process 2 sent message to process 3

Process 3 sent message to process 4

Process 4 sent message to process 5

Process 5 sent message to process 1

Process 1 sent message to process 2

Process 5 won the election

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
1

Enter process index :
5

Changed status of process 6 to active

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
3

Coordination Process index:
1
Process 2 held election

Process 2 sent message to process 3

Process 3 sent message to process 4

Process 4 sent message to process 5

Process 5 sent message to process 6

Process 6 sent message to process 1

Process 1 sent message to process 2

Process 6 won the election

Enter choice :
1) Up process
2) Down process
3) Hold election
4) Exit
Choice :
4

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 4 Election Algorithms>

*/