package com.slyone.review;

public class StringTest {
    public static void main(String[] args) {
        String s = new String("123456789ABCABC");
        print("Data is: " + s);
        print("Length is: " + s.length());

        int x = s.indexOf('A');
        print("The letter A was found at position " +
                x + " and " + s.indexOf('A', x + 1));

        print("The last occurence of the letter C " +
                "was found at position " + s.lastIndexOf('C'));

        print("substring(0, 9): " + s.substring(0, 9));

        print("substring(9): " + s.substring(9));

        print("toLowerCase(): " + s.toLowerCase());
    }

    public static void print(String s) {
        System.out.println(s);
    }
}