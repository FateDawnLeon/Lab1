package lab6;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class test {
    
    @Test
    public void test1(){
        Lab1 t1 = new Lab1();
        String exp = "x+y";
        exp = t1.transform_subtract(exp);
        exp = t1.transform_exponential(exp);
        ArrayList<ArrayList<Node>> arr = t1.expression(exp);
        String der = "!d/d 7x";
        t1.derivative(arr, der);
        String result = t1.result;
        assertEquals("Illegal Derivative Command",result);
    }
    
    @Test
    public void test2(){
        Lab1 t1 = new Lab1();
        String exp = "y+y*5*x*y";
        exp = t1.transform_subtract(exp);
        exp = t1.transform_exponential(exp);
        ArrayList<ArrayList<Node>> arr = t1.expression(exp);
        String der = "!d/d z";
        t1.derivative(arr, der);
        String result = t1.result;
        assertEquals("0",result);
    }
    
    @Test
    public void test3(){
        Lab1 t1 = new Lab1();
        String exp = "y+y*5*x*y";
        exp = t1.transform_subtract(exp);
        exp = t1.transform_exponential(exp);
        ArrayList<ArrayList<Node>> arr = t1.expression(exp);
        String der = "!d/d y";
        t1.derivative(arr, der);
        String result = t1.result;
        assertEquals("1+10*y*x",result);
    }
    
    @Test
    public void test4(){
        Lab1 t1 = new Lab1();
        String exp = "y-y*5*x*y";
        exp = t1.transform_subtract(exp);
        exp = t1.transform_exponential(exp);
        ArrayList<ArrayList<Node>> arr = t1.expression(exp);
        String der = "!d/d y";
        t1.derivative(arr, der);
        String result = t1.result;
        assertEquals("1-10*y*x",result);
    }
    
   
}
