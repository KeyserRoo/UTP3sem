package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Anagrams {

	private final Map<String, List<String>> anagramsMap;

	public Anagrams(String allWordsPath) {
		try {
			List<String> listOfWordsInFile = Files.lines(Paths.get(allWordsPath))
					.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.collect(Collectors.toList());

			this.anagramsMap = new HashMap<>();

			for (String word : listOfWordsInFile) {
				String key = countCharacters(word);

				if (!anagramsMap.containsKey(key)) {
					anagramsMap.put(key, new ArrayList<>());
				}
				anagramsMap.get(key).add(word);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String countCharacters(String str) {
		Map<Character, Long> charCount = str.chars()
				.mapToObj(inte -> (char) inte)
				.collect(Collectors.groupingBy(
						ch -> ch,
						TreeMap::new,
						Collectors.counting()));

		return charCount.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(entry -> entry.getKey() + ":" + entry.getValue())
				.collect(Collectors.joining(","));
	}

	public String getAnagramsFor(String word) {
		if (!anagramsMap.containsKey(countCharacters(word)))
			return word + ": " + null;

		List<String> anagrams = anagramsMap.getOrDefault(countCharacters(word),
				Collections.emptyList());
		anagrams.remove(word);
		return word + ": " + anagrams;
	}

	public List<List<String>> getSortedByAnQty() {
		List<List<String>> anagramLists = new ArrayList<>(anagramsMap.values());
		anagramLists.sort((list1, list2) -> {
			int sizeComparison = Integer.compare(list2.size(), list1.size());
			return (sizeComparison != 0) ? sizeComparison : list1.get(0).compareTo(list2.get(0));
		});
		return anagramLists;
	}

}