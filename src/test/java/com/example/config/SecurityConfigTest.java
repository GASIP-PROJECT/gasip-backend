package com.example.config;

import com.example.gasip.global.config.SecurityConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SecurityConfigTest {
    public static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SecurityConfig.class);

    public static void main(String[] args) {
        String[] bean = ac.getBeanDefinitionNames();
        for (String s : bean) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(s);
            System.out.println(beanDefinition);
        }
    }

}