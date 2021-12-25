package View;

import Model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Repo{
    private final List<Child> children;

    public Repo(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Child child : getChildren()) {
            s.append(child.toString()).append("\n");
        }
        return s.toString();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String[] readLetter(String letter) throws RuntimeException {
        try {
            StringBuilder data1 = new StringBuilder();
            File myObj = new File(letter);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data1.append(data);
                data1.append(" ");
            }
            myReader.close();

            String delim = " ";
            return data1.toString().split(delim);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void makeChildFromLetter(String letter1) {
        String[] args = readLetter(letter1);

        String Name = args[Arrays.asList(args).indexOf("am") + 1];
        String Age = args[Arrays.asList(args).lastIndexOf("am") + 1];
        String Country = Arrays.toString(new String[]{args[Arrays.asList(args).indexOf("at") + 1].split(",")[0]});
        String City = Arrays.toString(new String[]{args[Arrays.asList(args).indexOf("at") + 1].split(",")[1]});

        Address goodAddr =  new Address(Country.substring(1, Country.length() - 1), City.substring(1, City.length() - 2));
        String bhv = args[Arrays.asList(args).indexOf("very") + 1];
        Behavior bhv1;
        if (bhv.equals("Good")) {
            bhv1 = Behavior.Good;
        } else {
            bhv1 = Behavior.Bad;
        }

        String[] Presents = args[Arrays.asList(args).indexOf("is:") + 1].split(",");

        List<Item> items4 = new ArrayList<>(List.of());
        for (String i : Presents) {
            int flag = 0;
            for (Item i1 : getItems()) {
                if (i1.getName().equals(i)) {
                    items4.add(i1);
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                Item i7 = new Item(i);
                items4.add(i7);
            }

        }

        Letter l4 = new Letter(LocalDate.of(2021, getRandomNumber(1, 12), getRandomNumber(1, 31)), items4);
        Child c = new Child();
        c.setName(Name);
        c.setLetter(l4);
        c.setAddress(goodAddr);
        c.setDate_of_birth(String.valueOf(2021 - Integer.parseInt(Age)));
        c.setBhv(bhv1);
        addChild(c);

    }

    public void generateFile(Child c) throws IOException {
        String message = "forDear Santa,\n" + "I am " + c.getName() + "\n" + "I am " +
                (2021 - Integer.parseInt(c.getDate_of_birth())) + " years old. I live at " +
                c.getAddress() + ". I have been a very " + c.getBhv() + " child this year\n" +
                "What I would like the most this Christmas is:\n" + c.getLetter().getItems().get(0).toString().split(" ")[1]
                + "," + c.getLetter().getItems().get(1).toString().split(" ")[1];
        File file = new File("I:/materiale/chestii_diverse/WinterInternship2022-Backend/untitled/" + "letter" + c.getId().toString() + ".txt");
        if (!file.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("letter" + c.getId().toString() + ".txt"));
            writer.write(message);
            writer.close();
        }

    }

    public HashMap<String, Long> groupToys() {
        HashMap<String, Long> map = new HashMap<>();
        for (Child c : getChildren()) {
            for (Item i : c.getLetter().getItems()) {
                map.merge(i.toString().split(" ")[1], 1L, Long::sum);
            }
        }
        return map;
    }

    public Map<String, String> groupByCity() {
        HashMap<String, String> map = new HashMap<>();
        for (Child c : getChildren()) {
            map.putIfAbsent(c.getAddress().getCity(), c.getAddress().getCountry());
        }
        return map;
//        return map.keySet().stream()
//            .collect(Collectors.groupingBy(Function.identity(),
//                    Collectors.counting()));

    }

    public void addChild(Child c) {
        getChildren().add(c);
    }

    public int length() {
        return children.size();
    }

    public List<Child> getChildren() {
        return children;
    }

    public List<Item> getItems() {
        return getChildren().stream()
                .map(p -> p.getLetter().getItems())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
