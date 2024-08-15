package com.app;
import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

    public static String largestNumber(int[] nums) {
        // Convert all integers to strings
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = Integer.toString(nums[i]);
        }

        // Sort strings based on custom comparator
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                // Concatenate both possible orders and compare
                String xy = x + y;
                String yx = y + x;
                return yx.compareTo(xy); // Sort in descending order
            }
        });

        // If the largest number is "0", the result should be "0"
        if (strNums[0].equals("0")) {
            return "0";
        }

        // Build the largest number
        StringBuilder largestNum = new StringBuilder();
        for (String num : strNums) {
            largestNum.append(num);
        }

        return largestNum.toString();
    }

    public static void main(String[] args) {
        //int[] nums = {3, 30, 34, 5, 9};//[4,32,12,45,6,1] 
        int[] nums = {4,32,12,45,6,1};//[4,32,12,45,6,1] 
        System.out.println(largestNumber(nums));  // Output: "9534330"
    }
}
