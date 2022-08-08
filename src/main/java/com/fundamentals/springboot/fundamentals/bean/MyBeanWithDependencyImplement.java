package com.fundamentals.springboot.fundamentals.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private final Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    @Autowired
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }
    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWithDependency");
        int number1 = 2;
        int number2 = 2;
        LOGGER.debug("Los numeros enviados como parametros a la dependencia operation son: " + number1 +
                " y " + number2);
        System.out.println( myOperation.sum(number1, number2));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
