package zad3;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {
	private final LinkedHashMap<String, List<String>> langsMap;
	private final LinkedHashMap<String, List<String>> progsMap;

	public ProgLang(String fileName) throws IOException {
		langsMap = new LinkedHashMap<>();
		progsMap = new LinkedHashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\t");
				String lang = parts[0];

				Set<String> distinct = new LinkedHashSet<>(Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length)));
				List<String> progs = new LinkedList<>(distinct);

				langsMap.put(lang, progs);
				for (String prog : progs) {
					progsMap.computeIfAbsent(prog, k -> new ArrayList<>()).add(lang);
				}
			}
		}
	}

	public Map<String, List<String>> getLangsMap() {
		return new LinkedHashMap<>(langsMap);
	}

	public Map<String, List<String>> getProgsMap() {
		return new LinkedHashMap<>(progsMap);
	}

	public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
		Comparator<Map.Entry<String, List<String>>> comparator = Comparator
				.comparingInt((Map.Entry<String, List<String>> entry) -> entry.getValue().size())
				.reversed()
				.thenComparing(Map.Entry::getKey);

		return sorted(langsMap, comparator);
	}

	public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
		Comparator<Map.Entry<String, List<String>>> comparator = Comparator
				.comparingInt((Map.Entry<String, List<String>> entry) -> entry.getValue().size())
				.reversed()
				.thenComparing(Map.Entry::getKey);

		return sorted(progsMap, comparator);
	}

	public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
		Predicate<Map.Entry<String, List<String>>> predicate = (entry) -> {
			return entry.getValue().size() > 1;
		};

		return filtered(progsMap, predicate);
	}

	public static <K, V> Map<K, V> sorted(Map<K, V> inputMap, Comparator<Map.Entry<K, V>> comparator) {
		List<Map.Entry<K, V>> list = new ArrayList<>(inputMap.entrySet());
		Collections.sort(list, comparator);
		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> e : list) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}

	public <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate) {
		List<Map.Entry<K, V>> filteredList = new ArrayList<>(map.entrySet()).stream().filter(predicate)
				.collect(Collectors.toList());
		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> e : filteredList) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}

	@Override
	public String toString() {
		return "ProgLang{" +
				"langsMap=" + langsMap +
				", progsMap=" + progsMap +
				'}';
	}
}
