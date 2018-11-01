package com;

public class Worker2 extends Thread {
    private final int number;

    public Worker2(final int number) {
        this.number = number;
    }

    @Override
    public void run() {
        //        System.out.println("Current thread: " + Thread.currentThread().getName() + ", validating number " + this.number);
        if (Calculator.findPerfectNumber(this.number)) {
            System.out.println(this.number + "是完美數字喔");
            MainUseTreadPool.addToList(this.number);
        }
    }
}
