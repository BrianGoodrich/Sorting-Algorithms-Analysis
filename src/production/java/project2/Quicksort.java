package project2;
import java.util.concurrent.ThreadLocalRandom;
//Written by Brian Goodrich. The purpose of this program is to provide run times for the Quick Sort algorithm.
// Inputs are 3 arrays of 1,000, 10,000, and 100,000 integers with 3 different types of input values in these arrays, randomized integers from 1-10,000, sorted integers from 1-10,000 and partially sorted integers where every 10th integer is randomized.

public class Quicksort {

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

        System.out.println("The execution times of quicksort on an array of random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        sortedArray1k = quickSort(array1k, 0, (array1k.length - 1));
        sortedArray10k = quickSort(array10k, 0, (array10k.length - 1));
        sortedArray100k = quickSort(array100k, 0, (array100k.length -1));

        executionTime1 = runTime(sortedArray1k);
        executionTime2 = runTime(sortedArray10k);
        executionTime3 = runTime(sortedArray100k);

        System.out.println("The execution times of quicksort on an array of sorted random integers 1 - 10,000: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

        randomTen1k = randomize10th(sortedArray1k);
        randomTen10k = randomize10th(sortedArray10k);
        randomTen100k = randomize10th(sortedArray100k);

        executionTime1 = runTime(randomTen1k);
        executionTime2 = runTime(randomTen10k);
        executionTime3 = runTime(randomTen100k);

        System.out.println("The execution times of quicksort on an array of partially sorted random integers 1 - 10,000 where every 10th integer is randomized: \nArray size 1,000: " + executionTime1 + " nanoseconds\nArray size 10,000: " + executionTime2 + " nanoseconds\nArray size 100,000: " + executionTime3 + " nanoseconds");

    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    static int partition(int[] arr, int low, int high)
    {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot)
            {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    //Citation: (2022) Quick Sort [Source Code] https://www.geeksforgeeks.org/quick-sort/
    //This quicksort algorithm as well as the lomuto partition, and swap function was taken from the source above.
    public static int[] quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        return arr;
    }

    public static long runTime (int[] arr){

        long executionTime;
        long startTime;
        int n = arr.length;

        startTime = System.nanoTime();
        quickSort(arr, 0, n-1);
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
