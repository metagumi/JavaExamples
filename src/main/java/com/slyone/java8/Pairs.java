package com.slyone.java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class Pairs {
    public static void main(String[] args) {
        List<String> numbers1 = Arrays.asList("1","2","3");
        List<String> numbers2 = Arrays.asList("3","4");
        List<Object> pairs =
                numbers1.stream()
                    .flatMap(i -> numbers2.stream()
                                //.filter(j -> (i+j)
                                    //.map(j -> new String[] (i,j))
                    )
                            .collect(toList());
        System.out.println(pairs);

    }
}
