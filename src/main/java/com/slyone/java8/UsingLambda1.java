package com.slyone.java8;

import java.util.Arrays;
import java.util.List;

public class UsingLambda1 {
    public static void main(String[] args) {
        String [] atp =
                {"Rafael Nadal","Novak Djokovic","Stanislas Wawrinka","David Ferrer",
                "Roger Federer","Andy Murray","Tomas Berdych","Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        // old style looping
        for (String player : players)
            System.out.println(player + ";\n");

        // using lambda expression and functional operations
        players.forEach((player)-> System.out.println(player + ";\n"));

        // using double colon operator in Java 8
        players.forEach(System.out::println);
    }
}
