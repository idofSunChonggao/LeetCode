package com.leetcode.Q93;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author SunChonggao
 * @Date 2020/8/9 14:53
 * @Version 1.0
 * @Description:
 */
class Solution {
    private static final int SEG_COUNT = 4;
    private List<String> res = new ArrayList<>();//结果
    private int[] segment = new int[SEG_COUNT];

    private void dfs(String str, int start, int num) {
        if (num == SEG_COUNT) { //找到一种IP地址
            if (start == str.length()) {//并且字符串已经遍历完
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; i++) {
                    sb.append(segment[i]);
                    if (i != SEG_COUNT - 1)
                        sb.append('.');
                }
                res.add(sb.toString());
            }
            return;
        }
        if (start == str.length()) { // 还不够4段，但是字符串已经遍历结束
            return;//直接回溯，上一段ji

        }
        if (str.charAt(start) == '0') {//这一段以0开头,则递归下一段
            segment[num] = 0;
            dfs(str, start + 1, num + 1);

        } else {
            int addr = 0;
            for (int end = start; end < str.length(); end++) {
                addr = addr * 10 + (str.charAt(end) - '0');
                if (addr > 0 && addr < 256) {
                    //符合规则，记录地址
                    segment[num] = addr;
                    //递归下一段
                    dfs(str, end + 1, num + 1);
                } else
                    break;//此段过长，回溯至上一段
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        //递归回溯
        dfs(s, 0, 0);
        return res;

    }
}

public class IPAddress {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.restoreIpAddresses("1111"));
    }
}

