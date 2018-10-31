package com;

public class Worker extends Thread {
    private int startNumber =1;
    private int endNumber;

    public Worker(final int endNumber) {
        this.endNumber = endNumber;
    }
    
    public Worker(final int startNumber, final int endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    @Override
    public void run() {
        for (int i = this.startNumber; i <= this.endNumber; i++) {
            if(Calculator.findPerfectNumber(i)) {
                System.out.println(i + "是完美數字喔");
                MainUseTreadPool.addToList(i);
            }
        }
    }
}
