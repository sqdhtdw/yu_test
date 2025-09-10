package com.Lmall.common;

public class LouMallException extends RuntimeException {

    public LouMallException() {
    }

    public LouMallException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new LouMallException(message);
    }

}
