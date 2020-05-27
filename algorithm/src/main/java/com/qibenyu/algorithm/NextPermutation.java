package com.qibenyu.algorithm;

import java.util.Arrays;

/**
 * 找出数组排列中下一个比当前排列大的数
 *
 *
 * input：1, 2, 3
 * output: 1, 3, 2
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public String reverseWords(String s) {

        // StringBuilder sb = new StringBuilder(s);

        s = s.trim();
        int index = s.length() - 1;

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(index >= 0) {

            if (index == 0) {
                sb.append(s.substring(0,count + 1));
                break;
            }
            if(s.charAt(index) != ' ') {
                index--;
                count++;
            } else {
                if (count != 0) {
                    sb.append(s.substring(index + 1, index + count + 1)).append(' ');
                    count = 0;
                }
                index--;

            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        String s = new NextPermutation().reverseWords("a good   example");
//        System.out.println(s);
        int[] array = new int[]{2,3,1};
        new NextPermutation().nextPermutation(array);
        System.out.println(Arrays.toString(args));
    }

}
