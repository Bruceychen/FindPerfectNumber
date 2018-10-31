package com;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static boolean findPerfectNumber(int numbers) {
        // Algorithm for perfect number, return boolean.
            int root = 1;
            for (int count = 2; count < Math.sqrt(numbers); count++) {
                if (numbers % count == 0) {
                    root = root + count;
                    final int temp1 = numbers / count;
                    if (count != temp1) {
                        root = root + temp1;
                    }
                }
                if (root > numbers) {
                    break;
                }
            }
            if (root == numbers) {
                if (root == 1) {
                    return false;
                }
                return true;
            }
        return false;
    }
    
    //test method findPerfectNumber with single thread.
    public static void main(final String[] args) {
        final long startTime = System.currentTimeMillis();
        final List<Integer> resultList = new ArrayList<Integer>();
        System.out.print("搜尋中...");
        for (int i = 1; i <= 20000000; i++) {
            if(Calculator.findPerfectNumber(i)) {
                System.out.println(i + "是完美數字喔");
                resultList.add(i);
            }
        }
        System.out.println(resultList.toString());
        final long endTime = System.currentTimeMillis();
        System.out.println("結束應用");
        System.out.println("共用了" + (endTime - startTime)/1000 + "秒");
    }
}
