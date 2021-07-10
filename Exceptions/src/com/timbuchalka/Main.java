package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
	// write your code here



    }


    private static int divideLBYL(int x, int y) {
        // Look Before You Leap. Here we check that y is not zero.
        if(y != 0) {
            return x/y;
        } else {
            return 0;
        }



    }


    private static int divideEAFP(int x, int y) {
        try {
            return x/y;
        } catch(ArithmeticException e) {
            return 0;
        }
    }


}
