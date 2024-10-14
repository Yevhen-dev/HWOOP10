package hwOopTen;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		
		 try(FileReader reader = new FileReader("./text.txt")) {

			 int c;
			 Map<Character, Integer> map = new HashMap<>();
			 
			 while( ( c = reader.read() ) != -1 ) {
				 char key = (char) Character.toUpperCase(c);
				 
				 if( Character.isAlphabetic(key) ) {
					 map.put(key, map.getOrDefault(key, 0) + 1 );
				 }
			 }	
			 
			 Map<Character, Integer> sortedMap = map.entrySet().stream()
	                    .sorted(Map.Entry.comparingByValue())
	                    .collect(Collectors.toMap(
	                            Map.Entry::getKey,
	                            Map.Entry::getValue,
	                            (a, b) -> b,
	                            LinkedHashMap::new
	                    )).reversed();
			 
			 System.out.println( sortedMap );
			 
		 }catch(IOException e){
            e.printStackTrace();
        }  
		 
		 
		 Dictionary myDi = new Dictionary();
		 myDi.setName("english-ukrainian");
//		 myDi.createWordAndTranslate("fox", "лис");
		 myDi.createWordAndTranslate("dog", "пес");
		 myDi.createWordAndTranslate("apple", "яблуко");
		 myDi.createWordAndTranslate("zebra", "зебра");
		 System.out.println( myDi );
		 myDi.createAndSaveToFile();
		

	}

}
