package com.example.demo2.examples.junit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssertArrayListTeory {
    @Test
    public void miTest(){
       String [] arreg1={"a","b"};
        String [] arreg2={"a","b"};
        String [] arreg3={"a","b","c"};

        assertArrayEquals(arreg1,arreg2);
      // assertArrayEquals(arreg2,arreg3);
       // assertArrayEquals(arreg1,arreg3);
    }
}
