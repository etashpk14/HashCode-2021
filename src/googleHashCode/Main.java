package googleHashCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File myFile = new File("/home/etashpk14/GitHubProjects/NEBA-Project-2020/workspace_OGP/googleHashCode/f.txt");
		Scanner s = new Scanner(myFile);
		String[] input = s.nextLine().split(" ");
		//System.out.println(input[0]);
		int durationSim = Integer.parseInt(input[0]);
		int numIntersections = Integer.parseInt(input[1]);
		int numStreets = Integer.parseInt(input[2]);
		int numCars = Integer.parseInt(input[3]);
		int bonusPoints = Integer.parseInt(input[4]);
		Map<Integer, Set<Street>> intersMap = new HashMap<>();
		for (int i = 0; i < numIntersections; i++) {
			intersMap.put(i, new HashSet<Street>());
		}
		Map<String, Street> mapOfStreets = new HashMap<String, Street>();
		for (int i = 0; i < numStreets; i++) {
			input = s.nextLine().split(" ");
			Street street = new Street(input[2], Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[3]), new LinkedList<Car>(), 0);
			mapOfStreets.put(street.name, street);
			intersMap.get(street.end).add(street);
		}
		
		
		for (int i = 0; i < numCars; i++) {
			Car c = new Car(new LinkedList<Street>());
			input = s.nextLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(input[0]); j++) {
				c.streets.add(mapOfStreets.get(input[j]));
				mapOfStreets.get(input[j]).carsToPass++;
			}
			c.streets.peek().carsWaiting.add(c);		
		}
		
		int totalTime = durationSim / numIntersections;
		String output = "";
		int handledInters = 0;
		for (int i = 0; i < numIntersections; i++) {
			int totalCars = 0;
			int temp = 0;
			for (Street street : intersMap.get(i)) {
				totalCars += street.carsToPass;
				if (street.carsToPass != 0)
					temp++;
			}
			if (temp == 0)
				continue;
			output += Integer.toString(i) + "\n";
			output += Integer.toString(temp) + "\n";
			for (Street street : intersMap.get(i)) {
				if (street.carsToPass == 0)
					continue;
				output += street.name + " " + Integer.toString(street.carsToPass*totalTime/totalCars + 1) + "\n";
			}
			handledInters++;
		}
		
		try {
			FileWriter w = new FileWriter("/home/etashpk14/GitHubProjects/NEBA-Project-2020/workspace_OGP/googleHashCode/output6.txt");
			w.write(Integer.toString(handledInters) + "\n");
			w.write(output);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
