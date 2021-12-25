package com.company;

import Model.*;
import View.Repo;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static StringBuilder MapToString(Map<String,Long> map) {
        StringBuilder s = new StringBuilder();
        for(var elem: map.keySet()) {
            if (elem != null)
                s.append(elem).append(" -> ").append(map.get(elem).toString()).append('\n');
        }
        return s;
    }

    public static void Question1(Repo repo) {
        Item i1 = new Item("Zelda");
        Item i2 = new Item("books");
        List<Item> items1 = Arrays.asList(i1, i2);
        Letter l1 = new Letter(LocalDate.of(2021, 11, 15), items1);
        Address a1 = new Address("Romania", "Cluj");
        Child c1 = new Child("Tudor", "2012", a1, Behavior.Good, l1);
        repo.addChild(c1);
        System.out.println(c1);

        Item i3 = new Item("Barbie explorer");
        Item i4 = new Item("Pisicuta");
        List<Item> items2 = Arrays.asList(i3, i4);
        Letter l2 = new Letter(LocalDate.of(2021, 11, 20), items2);
        Address a2 = new Address("Romania", "Comanesti");
        Child c2 = new Child("Alexu", "2013", a2, Behavior.Good, l2);
        repo.addChild(c2);
        System.out.println(c2);

        //Item i5 = new Item("Zelda");
        Item i6 = new Item("Haine sh");
        List<Item> items3 = Arrays.asList(i1, i6);
        Letter l3 = new Letter(LocalDate.of(2021, 11, 7), items3);
        Address a3 = new Address("Romania", "Comanesti");
        Child c3 = new Child("Ionela", "2015",  a3, Behavior.Bad, l3);
        repo.addChild(c3);
        System.out.println(c3);
    }

    public static void Question2(String letter1, Repo repo) {
        try{
            repo.makeChildFromLetter(letter1);
            System.out.println(repo.getChildren().get(repo.length() - 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void Question3(Repo repo){
        for (Child child : repo.getChildren()) {
            try {
                repo.generateFile(child);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void Question4(Repo repo) {
        HashMap<String, Long> map = repo.groupToys();
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        StringBuilder s = MapToString(reverseSortedMap);
        s.insert(0, "Toys:\n");
        System.out.println(s);
    }

    public static void Question5() {
        //you can apply singleton design pattern but not to all the classes
        //because we need more different instances of the same type
        //but as we know, Santa Claus is unique so actually we should create
        //only an object Santa_Claus
        Santa_Claus santa_claus = Santa_Claus.getInstance();
        System.out.println("Singleton object: " + santa_claus.getName() + "\n");

    }

    public static void Question6(Repo repo) {
        Map<String, String> cities = repo.groupByCity();
        StringBuilder s = new StringBuilder();
        s.append("Grouped addresses by cities:\n");
        for(var elem: cities.entrySet()) {
            if (elem != null)
                s.append(elem.getKey()).append(",").append(elem.getValue()).append('\n');
        }
        System.out.println(s);

    }

    public static void main(String[] args){
        List<Child> children = new LinkedList<>();
        try{
            Repo repo = new Repo(children);
            Question1(repo);
            Question2("letter4.txt", repo);
            Question2("letter5.txt", repo);
            Question2("letter6.txt", repo);
            Question3(repo);
            Question4(repo);
            Question5();
            Question6(repo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
