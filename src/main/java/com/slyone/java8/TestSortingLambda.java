package com.slyone.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSortingLambda {
    public static void main(String[] args) {

        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer.getName().toString() +
                    " , " + developer.getSalary().toString() +
                    " , " + developer.getAge());
        }

        System.out.println("");
        System.out.println("After Sort by age 1");

        // Lambda here!
        listDevs.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
        // Java 8 only, lambda also, to print the list
        listDevs.forEach((developer) -> System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " , " +
                                                             developer.getAge()));
        System.out.println("");
        System.out.println("After Sort by Age 2");
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // As lambda
        listDevs.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
        listDevs.forEach((developer) ->System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " , " +
                                                            developer.getAge()));
        System.out.println("");
        System.out.println("After Sort by age 3");
        // lambda, valid, parameter type is optional
        listDevs.sort((o1, o2) -> o1.getAge() - o2.getAge());
        listDevs.forEach((developer)-> System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " , " +
                                                            developer.getAge()));

        // Sort by name
        System.out.println("");
        System.out.println("After Sort by name");
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // lambda
        listDevs.sort((Developer o1, Developer o2)-> o1.getName().compareTo(o2.getName()));
        listDevs.forEach((developer)->System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " , " +
                                                            developer.getAge()));

        // Sort by salary 2
        System.out.println("");
        System.out.println("After Sort by salary 2");
        Comparator<Developer> salaryComparator = (o1, o2) -> o1.getSalary().compareTo(o2.getSalary());
        listDevs.sort(salaryComparator);
        listDevs.forEach((developer)->System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " , " +
                                                            developer.getAge()));
        //Sort by salary reversed
        System.out.println("");
        System.out.println("After Sort by salary reversed");
        Comparator<Developer>salaryComparatorReversed = (o1, o2) ->o1.getSalary().compareTo(o2.getSalary());
        listDevs.sort(salaryComparatorReversed.reversed());
        listDevs.forEach((developer)->System.out.println(developer.getName().toString() + " , " +
                                                            developer.getSalary().toString() + " ," +
                                                            developer.getAge()));
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
