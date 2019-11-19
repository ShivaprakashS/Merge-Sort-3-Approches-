package leetcode;

import java.util.Arrays;
import java.util.Random;

public class SP3 {
    public static Random random = new Random();
    public static int numTrials = 1;
    public static void main(String[] args) {
	int n = 120400;  int choice = 1 + random.nextInt(4);
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
	if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
	    arr[i] = i;
	}
	Timer timer = new Timer();
	switch(choice) {
	case 1: //MergeSort Take1
		for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort1(arr);
	    }
		System.out.println(Arrays.toString(arr));
	    break;
	case 2: //MergeSort Take 3 and Take 4(Insertion Sort if size<Threshold (Threshold set to 10))
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort2(arr);
	    }
	    System.out.println(Arrays.toString(arr));
	    break; // etc 
	 case 3: //MergeSort Take 5 (Iterative Approach)
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort3(arr);
	    }
	    System.out.println(Arrays.toString(arr));
	    break;
	 case 4: //Insertion Sort
		 for(int i=0;i<numTrials;i++)
		 {
			 Shuffle.shuffle(arr);
			 insertionSort(arr);
		 }
		 System.out.println(Arrays.toString(arr));
		 break;
	}
	timer.end();
	timer.scale(numTrials);

	System.out.println("Choice: " + choice + "\n" + timer);
    }

    public static void insertionSort(int[] arr) {
    	
    	for(int i=1;i<arr.length;i++)
		{
			int temp=arr[i];
			int empty=i;
			while(empty>0 && arr[empty-1]>temp)
			{
				arr[empty]=arr[empty-1];
				empty=empty-1;
			}
			arr[empty]=temp;
		}
    	
    }

    public static void mergeSort1(int[] arr) {
    	
	  mergesort1(arr,0,arr.length-1);
			
    }
	
	public static void mergeSort2(int[] arr) {
		int[] B=new int[arr.length];
		for(int p=0;p<arr.length;p++)
		{
			B[p]=arr[p];
		}
		
		mergesort2(arr,B,0,arr.length-1);
		
    }

	public static void mergeSort3(int[] arr) {
		int[] B=new int[arr.length];
		for(int p=0;p<arr.length;p++)
		{
			B[p]=arr[p];
		}
		
		mergesort3(arr,0,arr.length-1);
		
    }

   /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

	public void scale(int num) { elapsedTime /= num; }
	
        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }
    
    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {
	
	public static void shuffle(int[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static<T> void shuffle(T[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static void shuffle(int[] arr, int from, int to) {
	    int n = to - from  + 1;
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	public static<T> void shuffle(T[] arr, int from, int to) {
	    int n = to - from  + 1;
	    Random random = new Random();
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}
	
	static<T> void swap(T[] arr, int x, int y) {
	    T tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}

	public static<T> void printArray(T[] arr, String message) {
	    printArray(arr, 0, arr.length-1, message);
	}

	public static<T> void printArray(T[] arr, int from, int to, String message) {
	    System.out.print(message);
	    for(int i=from; i<=to; i++) {
		System.out.print(" " + arr[i]);
	    }
	    System.out.println();
	}
    }




	
	
	public static void mergesort1(int[] A,int p,int r)
	{
		if(p<r)
		{
			int q=(p+r)/2;
			mergesort1(A,p,q);
			mergesort1(A,q+1,r);
			merge(A,p,q,r);
			
		}
	}
	
	public static void mergesort2(int[] A,int[] B,int p,int r)
	{
		if(r-p<10)
		{
			insertionSort(A);
		}
		else
		{
		if(p<r)
		{
			int q=(p+r)/2;
			mergesort2(B,A,p,q);
			mergesort2(B,A,q+1,r);
			merge(A,B,p,q,r);
			
		}
		}
	}
	
	public static void mergesort3(int[] A,int p,int r)
	{
		
		for(int i=1;i<=r-p;i=2*i)
		{
			for(int j=0;j<r-p;j=j+(2*i))
			{
				int from = j;
				int mid = Math.min(j+i-1, r-p);
				int to = Math.min(j+(2*i)-1, r-p);
				merge(A, from, mid, to);
			}
			
		}
	}
		
	
	
	//Copying array A to B in merge
	public static void merge(int[] A,int left,int mid,int r)
	{
		int k=left,p=0;
		int[] B=new int[r+1];
		for(p=left;p<=r;p++)
		{
			B[p]=A[p];
		}
		int i=left,j=mid+1;
		while(i<=mid && j<=r)
		{
			if(B[i]<=B[j])
			{
				A[k]=B[i];
				i++;
			}
			else
			{
				A[k]=B[j];
				j++;
			}
			k++;
		}
		while(i<=mid)
		{
			A[k]=B[i];
			i++;
			k++;
		}
		while(j<=r)
		{
			A[k]=B[j];
			j++;
			k++;
		}
		
	}
	
	//Avoiding copying of array A to array B in merge 
	public static void merge(int[] A,int[] B, int left,int mid,int r)
	{
		int k=left;
		int i=left,j=mid+1;
		while(i<=mid && j<=r)
		{
			if(B[i]<=B[j])
			{
				A[k]=B[i];
				i++;
			}
			else
			{
				A[k]=B[j];
				j++;
			}
			k++;
		}
		while(i<=mid)
		{
			A[k]=B[i];
			i++;
			k++;
		}
		while(j<=r)
		{
			A[k]=B[j];
			j++;
			k++;
		}
		
	}
	
	
	}
	
	

