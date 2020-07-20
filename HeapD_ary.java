import java.util.Arrays;



public class HeapD_ary {

	private int[] heap;//the heap array
	private int heapSize;//the number of the elements in the heap 
	private int d;//the heap base i.e. 'd'
	private final int LEN = 1000000;//our limit to the heap length
	
	/*
	 * constructor:
	 * @param arr - the array we read from the file
	 * @param numCild - our 'd' 
	 * will fill all the values in '-1' at default
	 */
	public HeapD_ary(int[] arr, int numChild) {
		this.heapSize = arr.length < LEN ? arr.length : -1;
		this.d = numChild;
		this.heap = new int[LEN];
		
		Arrays.fill(this.heap, -1);
		
		for(int i = 0; i < arr.length; i++)
			this.heap[i] = arr[i];
		this.buildHeap();
	}

	/*
	 * build a heap from arr[0..n-1]
	 */
	public void buildHeap() {
		//the last non leaf node will be at ⌊heapSize/d⌋ .  
		int i = (int) Math.floor(this.getHeapSize()/this.getD());
		//run upto the root node and heapify each one
		for(; i >= 0; i--)
			this.heapify(i);
	}
	/*
	 * insert a new element to the heap and place it in the right place
	 * @param key - the new value 
	 */
	public void insert(int key) { 
	    // Put the new element in the last position 
		this.getHeap()[this.getHeapSize()] = key; 
	    // Increase heap size by 1 
		this.setHeapSize(this.getHeapSize()+1);  
	    // Call heapifyUp on the last index 
		this.heapifyUp(this.getHeapSize()-1); 
	} 
	/*
	 * extract the max value in the heap
	 * reduce the heap size
	 * @return the old max 
	 */
	public int extractMax() {
		int max = this.getHeap()[0];
		this.getHeap()[0] = this.getHeap()[this.getHeapSize()-1];
		this.setHeapSize(this.getHeapSize()-1);
		this.heapify(0);
		return max;
	}
	/*
	 * Increases the key value of the node in the index i
	 * @param i - the index
	 * @param key - the new value
	 */
	public void increaseKey(int i, int key) {
		if(i > this.getHeapSize()) {
			System.err.println("index out of range");
			return;
		}
		if(key < this.getHeap()[i]) {
			System.err.println("the new key is smaller then the currnt key");
			return;
		}
		this.getHeap()[i] = key;
		this.heapifyUp(i);
	}
	/*
	 * delete a node from the heap
	 * reduce the heap size, and fix the heap if it needed
	 */
	public void delete(int i) {
		if(i > this.getHeapSize()) {
			System.err.println("index out of range");
			return;
		}
		swap(i, this.getHeapSize()-1);
		this.setHeapSize(this.getHeapSize()-1);
		this.heapify(i);
		
	}
	/*
	 * print the heap in the shape of the heap(approximately)
	 */
	public void printHeap() {
		/*layout = (number of elements in
		the last level(even if it is not full)*2)-1 because
		 we look at the spaces between. /2 because we want the root node exactly
		 in the middle*/
		printHeap((((int)Math.pow(this.getD(), logd_n())*2)-1)/2, 0, 0);
	}
	/*
	 * The method will perform recursive calling as the index i increases
	 *  each time by the number of children in all level in the heap
	 *  i.e. i += d^level.
	 *  In every call we grow to the next level in the heap, and print
	 *  out all the children in that level.
	 *  the goal of the second and the last loop
	 *  is to put spaces in a symmetrical layout(approximately)
	 *	the third loop run in total time of O(n), and hir goal is 
	 *	print the node in eche level un the heap. 
	 *  @param layout - see calculation in the overriding method 
	 *  @param i - the index of the first node in every level
	 *  @param level - the level we are at
	 */
	private void printHeap(double layout, int i, int level) {
		if(i >= this.getHeapSize() || layout < 0)
			return;
		//last level will be printed with one space between a node to a node 
		if(level == logd_n()) {//level = last level
			for(int j = i; j < this.getHeapSize(); j++)
				System.out.print(this.getHeap()[j] + " ");
			return;
		}
		//put spaces before the first node in the current level
		for (int j = 0; j < layout; j++) 
			System.out.print(" ");
		
		for(int numChild = (int)(Math.pow(this.getD(), level)),
				j = i; numChild > 0 && j < this.getHeapSize(); numChild--, j++) {
			//print all node in the current level
			System.out.print(this.getHeap()[j]);
			
			layout = layout == 0 ? layout+1 : layout;
			for (int k = 0; k < (layout*2)+1-(numOfDigit(this.getHeap()[j])-1); k++)
				System.out.print(" ");//put spaces between a node to a node
		}
		
		System.out.println();
		printHeap(Math.floor(layout/this.getD()), i+(int)(Math.pow(this.getD(), level)), level+1);
	}
	
	private int logd_n() {
		return (int)Math.floor(Math.log10(this.getHeapSize())/Math.log10(this.getD()));
	}

	private int numOfDigit(int x) {
		int count = 0;
		for(;x > 0; x/=10)
			count++;
		return count;
	}
	/*
	 * heapify for d-ary heap.
	 * input: the index that we want to download to is right place from
	 * top to bottom
	 * output: the index is placed in the heap hierarchy  
	 */
	private void heapify(int index) { 
		// child array to store indexes of all 
		// the children of given node 
		int[] child = new int[this.getD()+1]; 
		//endless loop. We will stop when
		//the node at which we stand in the heap is a leaf  
		while (0<1) { 
			// child[i]=-1 if the node is a leaf 
			for(int i = 1; i <= this.getD(); i++) { 
				if((this.getD() * index + i) < this.getHeapSize())//then it is not a leaf
					child[i] = this.getD() * index + i;
				else
					child[i] = -1;
			}
			// max_child stores the maximum child and 
			// max_child_index holds its index 
			int max_child = -1, max_child_index = 0; 
			// loop to find the maximum of all 
			// the children of a given node 
			for(int i = 1; i <= this.getD(); i++) {
				if (child[i] != -1 && this.getHeap()[child[i]] > max_child) { 
						max_child_index = child[i]; 
						max_child = this.getHeap()[child[i]]; 
					} 
			} 
			// leaf node 
			if (max_child == -1) 
				break; 
			
		    // swap only if the key of max_child_index 
			// is greater than the key of node 
			if (this.getHeap()[index] < this.getHeap()[max_child_index]) 
					swap(index ,max_child_index); 
			
			index = max_child_index; 
		} 
	}
	/*
	 * 
	 */
	private void heapifyUp(int index) {
		int parent = (index-1)/this.getD();   
	    // Loop should only run till root node in case the 
	    // element inserted is the maximum heapifyUp will 
	    // send it to the root node 
	    while (parent >= 0) { 
	        if (this.getHeap()[index] > this.getHeap()[parent]) { 
	            swap(index, parent); 
	            index = parent; 
	            parent = (index -1)/this.getD(); 
	        } 
	        // node has been restored at the correct position 
	        else
	            break; 
	    } 
	}

	public int getHeapSize() {
		if(heapSize == -1) {
			System.err.println("out of the limit");
			System.exit(1);
		}			
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int[] getHeap() {
		return this.heap;
	}

	private void swap(int i, int j) {
		int temp = this.getHeap()[i];
		this.getHeap()[i] = this.getHeap()[j];
		this.getHeap()[j] = temp;
	}

}
