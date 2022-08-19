package project2;
import java.util.concurrent.ThreadLocalRandom;
//Written by Brian Goodrich. The purpose of this program is to provide run times for the Selection Sort algorithm.
// Inputs are 3 arrays of 1,000, 10,000, and 100,000 integers with 3 different types of input values in these arrays, randomized integers from 1-10,000, sorted integers from 1-10,000 and partially sorted integers where every 10th integer is randomized.

public class SelectionSort {

    public static void main(String[] args) {

        int[] array1k = new int[1000];
        int[] array10k = new int[10000];
        int[] array100k = new int[100000];

        int[] sortedArray1k;
        int[] sortedArray10k;
        int[] sortedArray100k;

        int[] randomTen1k;
        int[] randomTen10k;
        int[] randomTen100k;

        long executionTime1;
        long executionTime2;
        long executionTime3;

        populateArray(array1k);
        populateArray(array10k);
        populateArray(array100k);

        executionTime1 = runTime(array1k);
        executionTime2 = runTime(array10k);
        executionTime3 = runTime(array100k);

        System.out.println("The execution times of selection sort on an array of random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        sortedArray1k = selectionSort(array1k);
        sortedArray10k = selectionSort(array10k);
        sortedArray100k = selectionSort(array100k);

        executionTime1 = runTime(sortedArray1k);
        executionTime2 = runTime(sortedArray10k);
        executionTime3 = runTime(sortedArray100k);

        System.out.println("The execution times of selection sort on an array of sorted random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        randomTen1k = randomize10th(sortedArray1k);
        randomTen10k = randomize10th(sortedArray10k);
        randomTen100k = randomize10th(sortedArray100k);

        executionTime1 = runTime(randomTen1k);
        executionTime2 = runTime(randomTen10k);
        executionTime3 = runTime(randomTen100k);

        System.out.println("The execution times of selection sort on an array of partially sorted random integers 1 - 10,000 where every 10th integer is randomized: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

    }

    //Citation: (2022) Selection Sort [Source Code] https://www.geeksforgeeks.org/selection-sort/
    //This selection sort algorithm was taken from the source above.
   public static int[] selectionSort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static long runTime (int[] arr){

        long executionTime;
        long startTime;

        startTime = System.nanoTime();
        selectionSort(arr);
        executionTime = (System.nanoTime() - startTime);

        return executionTime;
    }

    public static int[] randomize10th (int[] arr){

        for (int x = 9; x < arr.length; x += 10){

            arr[x] = ThreadLocalRandom.current().nextInt(1, 10000);
        }
        return arr;
    }

    public static int[] populateArray (int[] arr){

        for (int x = 0; x < arr.length; x++) {
            arr[x] = ThreadLocalRandom.current().nextInt(1, 10000);
        }
        return arr;
    }
}

