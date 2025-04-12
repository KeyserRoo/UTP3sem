package zad2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TowarOperator {

	private BlockingQueue<Towar> createdTowars = new LinkedBlockingQueue<>();
	private volatile boolean finishedProcessingFlag = false;
	
	private final Object lock = new Object();
	private int objectsCreated = 0;
	private double totalWeight = 0.0d;

	public void towarProducer(String path) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			reader.lines().forEach(line -> {
				String[] data = line.split("[ ]");
				int id = Integer.parseInt(data[0]);
				double weight = Double.parseDouble(data[1].replace(",", "."));

				try {
					createdTowars.put(new Towar(id, weight));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				synchronized (lock) {
					objectsCreated++;
					if (objectsCreated % 200 == 0)
						System.out.println("utworzono " + objectsCreated + " obiektów");
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		finishedProcessingFlag = true;
	}

	public void towarConsumer() {
		int counted = 0;
		while (!finishedProcessingFlag || !createdTowars.isEmpty()) {
			Towar towar = createdTowars.poll();
			if (towar != null) {
				synchronized (lock) {
					totalWeight += towar.getWeight();
					counted++;
					if (counted % 100 == 0)
						System.out.println("policzono wage " + counted + " towarów");
				}
			}
		}
	}

	public double getAverageWeight() {
		if (objectsCreated == 0)
			return 0;
		return totalWeight / objectsCreated;
	}
}
