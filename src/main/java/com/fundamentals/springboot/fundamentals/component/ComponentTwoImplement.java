package com.fundamentals.springboot.fundamentals.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void greet() {
        System.out.println("Hola mundo desde mi componente dos");
    }
}
