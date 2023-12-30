package com.example.demo2.examples.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calculator;
    @BeforeAll
    public static void first(){
        System.out.println("first");
    }
    @BeforeEach
    public void intanceObject(){

         calculator = new Calculator();
        System.out.println(" @BeforeEach");
    }

    @AfterEach
    public void lastTest(){
        System.out.println(" @AfterEach");

    }

    @AfterAll
    public static void last(){
        System.out.println("last");
    }



    @Test
    @DisplayName("prueba que ocupa assertEquals")
    @Disabled("esta test no se ejecutara")
    public void miTest() {
        Calculator calculator = new Calculator();

        assertEquals(2,calculator.add(1,1));
    assertEquals(2,calculator.subtract(3,1));
    assertEquals(3,calculator.multiply(3,1));
    assertEquals(1,calculator.divid(5,5));
        System.out.println(" miTest");
    }

@Test
    public void miTest2(){

    Calculator calculator = new Calculator();
    assertTrue(calculator.add(1,1)==2);
    assertTrue(calculator.subtract(1,1)==0);

    assertFalse(calculator.multiply(1,1)==2);

    assertFalse(calculator.divid(1,1)==2);
    System.out.println(" miTest2");

}



}

