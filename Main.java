import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)  {
		welcome();
	}

	@SuppressWarnings("resource")
	public static void welcome() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to maman 13\nEnter a file name, then press Enter," +
		" then enter 'd' to get d-ary");
		
		//make a new object of heap.
		//gets the array that was displayed in the text file,
		//and the heap base i.e. 'd'
		HeapD_ary h = new HeapD_ary(readFile(scan.next()), scan.nextInt());
		
		//endless loop for action selection
		while (0 < 1) {
			System.out.println("\n\npress 1: to print the heap\n" +
					"press 2: to insert a new value to the heap\n"+
					"press 3: to extract the max value\n" +
					"press 4: to increse key value of a node\n"+
					"press 5: to delete node\n" + "press 6: to change 'd'\n"+
					"press 7: to stop and exit");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				h.printHeap();
				break;
			case 2:
				System.out.println("\nenter a new key value");
				h.insert(scan.nextInt());
				break;
			case 3:
				System.out.println("\nmax is " + h.extractMax());
				break;
			case 4:
				System.out.println("\nenter a index of a node in the heap\nthat you want"
						+ " to increase and then enter the new key value");
				h.increaseKey(scan.nextInt(), scan.nextInt());
				break;
			case 5:
				System.out.println("\nenter a index that you want to delete");
				h.delete(scan.nextInt());
				break;
			case 6:
				System.out.println("\nenter a new 'd'");
				h.setD(scan.nextInt());
				break;
			case 7:
				System.out.println("Bye Bye!");
				return;
			default : 
				System.err.println("Wrong number\r\n" + 
					"You must select a number from 1-7");
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static int[] readFile(String fileName){
		try {
			File f = new File(fileName);//open file
			if(!f.exists()) {
				System.err.println("can't find a file!");
				System.exit(1);
			}
			Scanner scan = new Scanner(f);

			int len = 0;
			//get the length of the heap by counting the numbers in the file
			for (; scan.hasNextInt(); len++) 
				scan.nextInt();
			//check if the file is empty
			if(len == 0) {
				System.err.println("the file is empty!");
				System.exit(1);
			}
				
			int[] arr = new int[len];
			Scanner scan2 = new Scanner(f);
			//get the values from the file to array 
			for (int i = 0; i < arr.length; i++)
				arr[i] = scan2.nextInt();
			
			scan.close();
			scan2.close();
			
			return arr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
