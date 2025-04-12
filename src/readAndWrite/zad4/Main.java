package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("unixdict.txt"))) {
			words = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, List<String>> anagrams = new HashMap<>();
		for (String word : words) {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);
			anagrams.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
		}

		int maxAnagrams = anagrams.values().stream()
				.mapToInt(List::size)
				.max()
				.orElse(0);

		List<Map.Entry<String, List<String>>> maxAnagramWords = anagrams.entrySet().stream()
				.filter(entry -> entry.getValue().size() == maxAnagrams)
				.collect(Collectors.toList());

		for (Map.Entry<String, List<String>> entry : maxAnagramWords) {
			System.out.println(entry.getKey() + " " + String.join(" ", entry.getValue()));
		}
	}
}
