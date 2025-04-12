package zad1;

public class Purchase {

  private String sNum;

  private String name;

  private String surname;

  private String product;

  private double price;

  private double amount;

  private final String fullLine;

  public Purchase(String line) {
    this.fullLine = line.trim();
    String[] data = line.split(";");

    this.sNum = data[0];
    this.name = data[1].split(" ")[1];
    this.surname = data[1].split(" ")[0];
    this.product = data[2];
    this.amount = Double.parseDouble(data[3]);
    this.price = Double.parseDouble(data[4]);
  }

  public String getsNum() {
    return sNum;
  }

  public void setsNum(String sNum) {
    this.sNum = sNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getFullLine() {
    return fullLine;
  }

  @Override
  public String toString() {
    return "Purchase [sNum=" + sNum + ", name=" + name + ", surname=" + surname + ", product=" + product
        + ", price=" + price + ", amount=" + amount + ", fullLine=" + fullLine + "]";
  }

}