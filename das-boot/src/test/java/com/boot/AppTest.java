package com.boot;

import org.junit.Test;
import static org.junit.Assert.*;
import com.boot.controller.HomeController;

public class AppTest
{
    @Test
	public void testApp()
    {
    	HomeController hc = new HomeController();
    	String result = hc.home();
    	assertEquals(result,"das boot reporting for duty!");

    }
}
