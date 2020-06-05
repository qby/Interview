package com.qibenyu.algorithm;

public class SearchRotatedArray {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while(left <= right) {

            int mid = (left + right) / 2;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]) {
                if(nums[left] <= target && nums[mid] >= target) {
                    right= mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[mid] <= target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotatedArray searchRotatedArray = new SearchRotatedArray();

        int search = searchRotatedArray.search(new int[]{
                4, 5, 6, 7, 0, 1, 2
        }, 0);

        System.out.println(search);
    }
}
