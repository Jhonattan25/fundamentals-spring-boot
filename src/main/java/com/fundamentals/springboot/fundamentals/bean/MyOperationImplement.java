package com.fundamentals.springboot.fundamentals.bean;

public class MyOperationImplement implements MyOperation{
    @Override
    public int sum(int number1, int number2) {
        return number1 + number2;
    }
}
