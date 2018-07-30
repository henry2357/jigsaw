package com.henry.jigsaw.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void hello() {
        System.out.println("hello:" + userName);
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
        HelloSpring helloWorld = (HelloSpring) ctx.getBean("helloWorld");
        helloWorld.hello(); // 输出 hello:spring
    }
}
