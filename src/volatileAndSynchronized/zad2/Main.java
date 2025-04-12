package zad2;

public class Main {
  private static final String FILE_PATH = "../Towary.txt";

  public static void main(String[] args) {
    TowarOperator towarOperator = new TowarOperator();

    Thread producerThread = new Thread(() -> towarOperator.towarProducer(FILE_PATH));
    Thread consumerThread = new Thread(towarOperator::towarConsumer);

    producerThread.start();
    consumerThread.start();

    try {
      producerThread.join();
      consumerThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    
    System.out.println("Average weight: " + towarOperator.getAverageWeight());
  }
}
