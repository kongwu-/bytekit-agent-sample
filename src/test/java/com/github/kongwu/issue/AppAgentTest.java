package com.github.kongwu.issue;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppAgentTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        classPathXmlApplicationContext.start();
    }
}
