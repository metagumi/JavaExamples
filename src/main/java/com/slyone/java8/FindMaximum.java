package com.slyone.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindMaximum {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4,5,3,9);
        Optional<Integer> max = numbers.parallelStream().reduce(Integer::max);

        System.out.println("max = " + max);

        Optional<Integer> min = numbers.parallelStream().reduce(Integer::min);

        System.out.println("min = " + min);
    }
}
