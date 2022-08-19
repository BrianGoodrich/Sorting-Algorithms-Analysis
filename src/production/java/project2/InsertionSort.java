package project2;
import java.util.concurrent.ThreadLocalRandom;
//Written by Brian Goodrich. The purpose of this program is to provide run times for the Insertion Sort algorithm.
// Inputs are 3 arrays of 1,000, 10,000, and 100,000 integers with 3 different types of input values in these arrays, randomized integers from 1-10,000, sorted integers from 1-10,000 and partially sorted integers where every 10th integer is randomized.

public class InsertionSort {

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

        System.out.println("The execution times of insertion sort on an array of random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        sortedArray1k = insertionSort(array1k);
        sortedArray10k = insertionSort(array10k);
        sortedArray100k = insertionSort(array100k);

        executionTime1 = runTime(sortedArray1k);
        executionTime2 = runTime(sortedArray10k);
        executionTime3 = runTime(sortedArray100k);

        System.out.println("The execution times of insertion sort on an array of sorted random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        randomTen1k = randomize10th(sortedArray1k);
        randomTen10k = randomize10th(sortedArray10k);
        randomTen100k = randomize10th(sortedArray100k);

        executionTime1 = runTime(randomTen1k);
        executionTime2 = runTime(randomTen10k);
        executionTime3 = runTime(randomTen100k);

        System.out.println("The execution times of insertion sort on an array of partially sorted random integers 1 - 10,000 where every 10th integer is randomized: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

    }

    //Citation: (2021) Insertion Sort [Source Code] https://www.geeksforgeeks.org/insertion-sort/
    //This insertion sort algorithm was taken from the source above.
    public static int [] insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0...i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static long runTime (int[] arr){

        long executionTime;
        long startTime;

        startTime = System.nanoTime();
        insertionSort(arr);
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
