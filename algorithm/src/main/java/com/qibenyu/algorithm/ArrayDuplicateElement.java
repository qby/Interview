package com.qibenyu.algorithm;

public class ArrayDuplicateElement {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {

            if(nums[i] != i) {
                if(nums[nums[i]] == i) {
                    return i;
                } else {
                    int index = nums[i];
                    int x = nums[index];
                    nums[i] = x;
                    nums[index] = index;
                    continue;
                }

            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int repeatNumber = new ArrayDuplicateElement().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        System.out.println(repeatNumber);

    }
}
