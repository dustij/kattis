package templates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapPermutations {

    public static void main(String[] args) {
        int[] operands = {1, 2, 3, 4};
        List<int[]> permutations = generatePermutations(operands);

        // Print all generated permutations
        for (int[] perm : permutations) {
            System.out.println(Arrays.toString(perm));
        }
    }

    // Generate all permutations of the input array
    public static List<int[]> generatePermutations(int[] nums) {
        List<int[]> result = new ArrayList<>();
        heapPermutation(nums.length, nums, result);
        return result;
    }

    // Heap's Algorithm to generate permutations
    private static void heapPermutation(int size, int[] nums, List<int[]> result) {
        if (size == 1) {
            result.add(nums.clone()); // Add permutation to result
            return;
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(size - 1, nums, result);

            // Swap logic based on even or odd size
            if (size % 2 == 1) {
                swap(nums, 0, size - 1);
            } else {
                swap(nums, i, size - 1);
            }
        }
    }

    // Swap two elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
