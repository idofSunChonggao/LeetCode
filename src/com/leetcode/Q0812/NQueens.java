package com.leetcode.Q0812;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author SunChonggao
 * @Date 2020/8/9 10:22
 * @Version 1.0
 * @Description:
 */
class Solution {
    private List<List<String>> res;
    private List<String> sol;
    private int[] que;//皇后位置，索引为行，值为列
    private int N;
    private int solution = 0;
    private void search(int cur) {// cur为当前行数
        if(cur == N) { //找到一种解法
            solution++;
            sol = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                StringBuffer col = new StringBuffer();
                for(int j = 0; j < N; j++) {
                    if(que[i] == j)
                        col.append('Q'); // 是皇后
                    else
                        col.append('.');
                }
                sol.add(col.toString());
            }
            res.add(sol);
        }
        else {
            if(cur == 0) {
                //初始化皇后数组
                for(int i = 0; i < N; i++)
                    que[i] = -1;
            }
            for(int i = 0; i < N; i++) { // 尝试把皇后放在（cur，i）位置
                int j;
                for (j = 0; j < cur; j++) { // 已经摆放的皇后（j, que[j])
                    //和已经摆放的皇后比较
                    if (que[j] == i || cur + i == j + que[j] || cur - i == j - que[j]) { //冲突
                        break;//跳出循环，不需要继续比较
                    }
                }
                if (j == cur) {//没有冲突，可以摆放
                    que[cur] = i;//记录皇后位置
                    //sol.add("Q");
                    search(cur + 1);//继续寻找下一行
                    //回溯
                    que[cur] = -1;//清除位置
                }
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        //递归回溯
        res = new ArrayList<>();
        N = n;
        que = new int[n];
        search(0);
        System.out.println(solution);
        return res;
    }
}
public class NQueens {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solveNQueens(4);
    }
}
