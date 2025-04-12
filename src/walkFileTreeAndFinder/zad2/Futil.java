package zad1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Futil {
  public static void processDir(String directory, String resultFile) {
    try {
      Path resultPath = Paths.get(resultFile);
      Files.createFile(resultPath);

      BufferedWriter writer = Files.newBufferedWriter(resultPath, StandardCharsets.UTF_8);

      Files.walk(Paths.get(directory))
          .filter(path -> path.toString().endsWith(".txt"))
          .forEach(file -> {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
              reader.lines().forEach(line -> {
                try {
                  writer.write(line);
                  writer.newLine();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
            } catch (IOException e) {
              e.printStackTrace();
            }
          });

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}