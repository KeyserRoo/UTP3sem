package zad2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Lista destynacji: port_wylotu port_przylotu cena_EUR
        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = dest
                .stream()
                .filter((String item) -> item.startsWith("WAW"))
                .map((String item) -> {
                    String[] elements = item.split(" ");
                    return "to " + elements[1] + " - price in PLN: " + (int)(Integer.parseInt(elements[2])*ratePLNvsEUR);
                })
                .collect(Collectors.toList());

        for (String r : result) System.out.println(r);
    }
}