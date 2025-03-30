package com.crm;

public class A {
    int x = 10;
    public static void main(String[] args){
        A a1 = new A();
        System.out.println(a1.x); // Outputs: 10
        int y = a1.test();
        System.out.println(y); // Outputs: 100
    }
public int test(){
        return 100;
}
}
