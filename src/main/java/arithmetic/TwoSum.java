package arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 */
public class TwoSum {


    /**
     * 思路：
     * 判断当前值，距离目标缺少的值，在不在当前数组中
     * 当前值判断，盒子里没有的，就先放到盒子里，然后判断下一个值缺少的值在不在盒子里。。。。
     * 盒子用的是map，即可以存当前的值，也可以存当前值在队列中的位置
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int cha = target - nums[i];
            if (map.get(cha) != null){
                return new int[]{map.get(cha),nums[i]};
            }
            map.put(nums[i],i);
        }

        return new int[0];
    }

    /**
     * 虽然可以解决问题，但是两层for循环，时间复杂度O(n*n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {

            for (int j = i+1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }

        return new int[0];
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,7,3,8,11,4,9};
        int target = 20;
        int[] result = twoSum2(nums,target);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + "\t");
        }
    }
}
