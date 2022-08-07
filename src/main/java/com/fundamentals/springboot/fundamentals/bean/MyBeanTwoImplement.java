package com.fundamentals.springboot.fundamentals.bean;

public class MyBeanTwoImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion 2 propia del bean");
    }
}
