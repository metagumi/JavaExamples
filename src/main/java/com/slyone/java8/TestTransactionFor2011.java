package com.slyone.java8;

import java.util.Arrays;
import java.util.List;

public class TestTransactionFor2011 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
        transactions.stream()
                .filter(d -> d.getYear() == 2011)
                //.filter(d -> d.getYear( == 2011 ||
                //              d.getYear( == 2012)
                .map(Transaction::getValue).sorted()
                .forEach(System.out::println);
    }
}
