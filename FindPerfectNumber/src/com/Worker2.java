package com;

public class Worker2 extends Thread {
    private int number;

    public Worker2(final int number) {
        this.number = number;
    }
    
    @Override
    public void run() {
            if(Calculator.findPerfectNumber(number)) {
                System.out.println(number + "是完美數字喔");
                MainUseTreadPool.addToList(number);
            }
    }
}
