package com.slyone.java8;

//import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestTraderByUniqueCities {
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

        //========================================================
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList());

        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct().sorted(Comparator.comparing(Trader::getName))
                .collect(toList());

        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName() + " ")
                .distinct().sorted()
                //reduce("", (n1,n2) -> n1 + " " + n2);
                .collect(Collectors.joining());

        boolean milanBased =
                transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                                                    .getCity()
                                                    .equals("Milan"));

        transactions.stream()
                    .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        Optional<Integer> highestTransaction =
                transactions.stream()
                            .map(Transaction::getValue)
                            .reduce(Integer::max);

        Optional<Transaction> smallestTransaction =
                    transactions.stream()
                                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> smallestTransaction2 =
                    transactions.stream()
                                .min(Comparator.comparing(Transaction::getValue));

        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700, Dish.Type.MEAT),
                new Dish("chicken",false,400, Dish.Type.MEAT),
                new Dish("french fries",true,530, Dish.Type.OTHER),
                new Dish("rice",true,350, Dish.Type.OTHER),
                new Dish("season fruit",true,120, Dish.Type.OTHER),
                new Dish("pizza",true,550, Dish.Type.OTHER),
                new Dish("prawns",false,700, Dish.Type.FISH),
                new Dish("salmon",false,450, Dish.Type.FISH));

        // boxing cost
        int calories = menu.stream()
                            .map(Dish::getCalories)
                            .reduce(0, Integer::sum);

        //no boxing cost
        int caloriesSum = menu.stream()
                                .mapToInt(Dish::getCalories)
                                .sum();

        //no boxing cost
        OptionalInt maxCalories = menu.stream()
                                        .mapToInt(Dish::getCalories)
                                        .max();

        int max = maxCalories.orElse(1);

        IntStream evenNumbers = IntStream.rangeClosed(1,100)
                        .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count() + " even numbers in 1..100");

        System.out.println(cities);
        System.out.println(traders);
        System.out.println(traderStr);
        System.out.println(milanBased);
        System.out.println(highestTransaction);
        System.out.println(smallestTransaction);
        System.out.println(smallestTransaction2);
        System.out.println(calories + " Calories");
        System.out.println(caloriesSum  + " Calories using mapToInt method");
        System.out.println(maxCalories + " Calories");
        System.out.println(max + " Caloriesusing maxCalories.orElse");

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a ->
                            IntStream.rangeClosed(a,100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b -> new int[] {a,b, (int)Math.sqrt(a*a + b*b)}));
        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a,100)
                                        .mapToObj(b -> new double[] {a,b, (int)Math.sqrt(a*a + b*b)}));
        pythagoreanTriples2.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        long uniqueWords = 0;
        try (Stream<String> lines =
                    Files.lines(Paths.get("C:\\data\\data.txt"),Charset.defaultCharset())) {
                    uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                            .distinct()
                            .count();
                    System.out.println("Unique word count = " + uniqueWords);
                } catch (IOException e1) {
                        e1.printStackTrace();
                }

                Stream.iterate(0, n -> n + 2)
                        .limit(10)
                        .forEach(System.out::println);

                Stream.iterate(new int[]{0,1},
                        t -> new int[] {t[1], t[0]+t[1]})
                        .limit(20)
                        .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

                Stream.iterate(new int[]{0,1},
                        t -> new int[] {t[1], t[0] + t[1]})
                        .limit(10)
                        .map(t -> t[0])
                        .forEach(System.out::println);

                Stream.generate(Math::random)
                        .limit(5)
                        .forEach(System.out::println);

                IntSupplier fib = new IntSupplier() {
                    private int previous = 0;
                    private int current = 1;

                    public int getAsInt() {
                        int oldPrevious = this.previous;
                        int nextValue = this.previous + this.current;
                        this.previous = this.current;
                        this.current = nextValue;
                        return oldPrevious;
                    }
                };
                IntStream.generate(fib).limit(10).forEach(System.out::println);

    }
}
