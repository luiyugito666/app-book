package com.example.demo2.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTrueFlaseTeory {

    @Test
    public void miTest(){
        assertTrue(true);
        assertFalse(false);

    }

    @Test
    public void miTest2(){
        boolean exp= 4==43;
        assertFalse(exp);
       // assertTrue(exp);


    }
}
