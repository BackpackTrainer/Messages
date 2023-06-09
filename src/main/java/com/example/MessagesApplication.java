package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MessagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesApplication.class, args);
    }

//        @Bean
//        public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//            return args -> {
//
//                System.out.println("\nWhat beans does Spring create when we start the app?  These:");
//
//                String[] beanNames = ctx.getBeanDefinitionNames();
//                Arrays.sort(beanNames);
//                for (String beanName : beanNames) {
//                    System.out.println(beanName);
//                }
//                System.out.println("\nSpring created " + beanNames.length + " beans");
//            };
//        }
}
