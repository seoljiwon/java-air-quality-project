import java.io.*;  
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\Àlex Esteban\\Desktop\\Uni\\INSA-IST-SOE\\Sensors.csv";
		Scanner sensors = new Scanner(new File(path));
		System.out.println(sensors.next());
		while (sensors.hasNext()) {
			String[] line = sensors.next().split(";");
			System.out.println(line[1]);  
		}   
		sensors.close();    
	}
}
