package com.epam.msu.config;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<>();
        fillTheCollection(collection);
        Integer sumOfOdd = 0;
        /*for(Integer number: collection){
            if(number%2==0){
                sumOfOdd+=number;
            }
        }*/
        sumOfOdd = collection.stream().filter(p -> p %2==0).reduce((s1,s2) -> s1 + s2).orElse(0);

        System.out.println(sumOfOdd);
        System.out.println(
                System.getProperty("name") + " " + System.getProperty("password")
        );
    }

    private static void fillTheCollection(List<Integer> collection) {
        for (int i = 1; i <= 10; i++) {
            Integer number = i;
            collection.add(number);
        }
    }
}
