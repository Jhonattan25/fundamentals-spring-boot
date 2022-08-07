package com.fundamentals.springboot.fundamentals.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private MyOperation myOperation;

    @Autowired
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int number1 = 2;
        int number2 = 2;
        System.out.println( myOperation.sum(number1, number2));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
