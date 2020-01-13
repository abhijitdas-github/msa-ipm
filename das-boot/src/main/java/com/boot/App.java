package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

/*
 * Combination of @Configuration, @ComponentScan, @EnableAutoConfiguration
 */
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	SpringApplication.run(App.class, args);
    }
}
