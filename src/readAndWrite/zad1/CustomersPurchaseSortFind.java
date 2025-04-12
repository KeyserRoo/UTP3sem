package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
  private List<Purchase> purchaseList = new ArrayList<>();

  public void readFile(String fname) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fname))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Purchase purchase = new Purchase(line);
        purchaseList.add(purchase);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void showSortedBy(String sortBy) {
    System.out.println(sortBy);

    Comparator<Purchase> comparator = sortBy.equals("Nazwiska")
        ? (Purchase p1, Purchase p2) -> p1.getSurname().compareToIgnoreCase(p2.getSurname())
        : Comparator.comparingDouble(p -> p.getAmount() * p.getPrice() * -1);

    purchaseList.stream()
        .sorted(comparator.thenComparing((Purchase p1, Purchase p2) -> p1.getsNum().compareToIgnoreCase(p2.getsNum())))
        .forEach(p -> {
          System.out.print(p.getFullLine());
          if (sortBy.equals("Koszty")) {
            double price = p.getPrice() * p.getAmount();
            System.out.print(" (koszt: " + price + ")");
          }
          System.out.println();
        });
    System.out.println();
  }

  public void showPurchaseFor(String clientId) {
    System.out.println("Klient " + clientId);
    for (Purchase purchase : purchaseList) {
      if (purchase.getsNum().equals(clientId)) {
        System.out.println(purchase.getFullLine());
      }
    }
    System.out.println();
  }
}
