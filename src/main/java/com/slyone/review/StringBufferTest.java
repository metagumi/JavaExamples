package com.slyone.review;

public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(32);
        info(sb);
        info(sb.append(123456789));
        info(sb.insert(0, "abcdefghi"));
        info(sb.replace(2,5,"Hello"));
        sb.setLength(0);
        info(sb);
    }
    public static void info(StringBuffer sb) {
        System.out.println("len: "  + sb.length());
        System.out.println(" cap: "  + sb.capacity());
        System.out.println(" data: "  + sb);
        System.out.println();
    }
}