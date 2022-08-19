package project2;
import java.util.concurrent.ThreadLocalRandom;
//Written by Brian Goodrich. The purpose of this program is to provide run times for the Merge Sort algorithm.
// Inputs are 3 arrays of 1,000, 10,000, and 100,000 integers with 3 different types of input values in these arrays, randomized integers from 1-10,000, sorted integers from 1-10,000 and partially sorted integers where every 10th integer is randomized.

public class Mergesort {

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

        System.out.println("The execution times of mergesort on an array of random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        sortedArray1k = mergeSort(array1k, 0, (array1k.length - 1));
        sortedArray10k = mergeSort(array10k, 0, (array10k.length - 1));
        sortedArray100k = mergeSort(array100k, 0, (array100k.length -1));

        executionTime1 = runTime(sortedArray1k);
        executionTime2 = runTime(sortedArray10k);
        executionTime3 = runTime(sortedArray100k);

        System.out.println("The execution times of mergesort on an array of sorted random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        randomTen1k = randomize10th(sortedArray1k);
        randomTen10k = randomize10th(sortedArray10k);
        randomTen100k = randomize10th(sortedArray100k);

        executionTime1 = runTime(randomTen1k);
        executionTime2 = runTime(randomTen10k);
        executionTime3 = runTime(randomTen100k);

        System.out.println("The execution times of mergesort on an array of partially sorted random integers 1 - 10,000 where every 10th integer is randomized: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

    }

    public static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //Citation: (2022) Merge Sort [Source Code] https://www.geeksforgeeks.org/merge-sort/
    //This mergesort algorithm as well as the merge function was taken from the source above.
    // Main function that sorts arr[l..r] using
    // merge()
    public static int[] mergeSort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        return arr;
    }

    public static long runTime (int[] arr){

        long executionTime;
        long startTime;
        int n = arr.length;

        startTime = System.nanoTime();
        mergeSort(arr, 0, n-1);
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
