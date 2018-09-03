package com.slyone.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSorting {
    public static void main(String[] args) {
        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort by age");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

        //sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        System.out.println("After Sort by age");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

        System.out.println("Before Sort by name");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

        // sort by name
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println("After Sort by name");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

        System.out.println("Before Sort by salary");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

        // sort by salary
        //sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });

        System.out.println("After Sort by salary");
        for(Developer developer : listDevs) {
            System.out.println(developer.getName().toString() + " , " +
                    developer.getSalary().toString() + " , " +
                    developer.getAge());
        }

    }

    private static List<Developer> getDevelopers() {
        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("slyone", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 33));

        return result;
    }
}
