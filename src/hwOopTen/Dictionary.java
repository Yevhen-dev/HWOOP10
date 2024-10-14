package hwOopTen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Dictionary {

	private String name;
	private Map<String, String> wordAndTranclate = new TreeMap<>();

	public Dictionary(String name, Map<String, String> wordAndTranclate) {
		super();
		this.name = name;
	}

	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getWordAndTranclate() {
		return wordAndTranclate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, wordAndTranclate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dictionary other = (Dictionary) obj;
		return Objects.equals(name, other.name) && Objects.equals(wordAndTranclate, other.wordAndTranclate);
	}

	@Override
	public String toString() {
		return "Dictionary [name=" + name + ", wordAndTranclate=" + wordAndTranclate + "]";
	}

	public Map createWordAndTranslate(String word, String translate) {

		File file = new File(name + ".csv");

		if (file.exists()) {
			try {
				TreeMap<String, String> map = synchronizedWithFile();
				for (String key : map.keySet()) {
					wordAndTranclate.put(key, map.get(key));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			wordAndTranclate.put(word, translate);
		}

		return wordAndTranclate;
	}

	public void createAndSaveToFile() throws IllegalArgumentException {

		if (name.equals(null)) {
			new IllegalAccessException("Create name for dictionary");
		} else {
			String result;
			File file = new File(name + ".csv");

			try (BufferedWriter writter = new BufferedWriter(new FileWriter(file))) {
				for (String key : wordAndTranclate.keySet()) {
					writter.write(key + " - " + wordAndTranclate.get(key) + "\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public TreeMap synchronizedWithFile() throws IOException {

		TreeMap<String, String> map = new TreeMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(name + ".csv")))) {
			String line;
			while ((line = br.readLine()) != null) {
				map.put(line.split(" - ")[0], line.split(" - ")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

}
