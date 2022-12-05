import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class byplace {

	public static String sensorLocation(String latitude, String longitude) throws FileNotFoundException {

		// search the most near sensor and returns the value of the area

		// open files

		String locationPath = "./src/data/AttributeType.csv";
		Scanner location = new Scanner(new File(locationPath));

		String dataPath = "./src/data/data.csv";
		Scanner data = new Scanner(new File(dataPath));
		String firstLine = data.nextLine();

		// calculate value
		double latitudenum = Double.parseDouble(latitude);
		double longitudenum = Double.parseDouble(longitude);
		String sensor = "";

		double prevLong = Math.sqrt(Math.pow(latitudenum, 2) + Math.pow(longitudenum, 2));
		while (data.hasNext()) {

			String[] columns = data.next().split(";");
			double calculelong = Math.sqrt(Math.pow(latitudenum - Double.parseDouble(columns[1]), 2)
					+ Math.pow(longitudenum - Double.parseDouble(columns[2]), 2));

			if (calculelong < prevLong) {
				sensor = columns[0];

			}
		}

		// close files
		location.close();
		data.close();

		return sensor;
	}

	public static void sensorValues(String sensor) throws FileNotFoundException {
		String vala="O3";String valb="SO2"; String valc="NO2"; String vald="PM10";
		//We obtain all the values
		Double valuea=meanValueLocationAttribute(sensor,vala);
		Double valueb=meanValueLocationAttribute(sensor,valb);
		Double valuec=meanValueLocationAttribute(sensor,valc);
		Double valued=meanValueLocationAttribute(sensor,vald);

		System.out.println("The mean value of O3 in "+sensor+" is: "+valuea);
		System.out.println("The mean value of SO2 in "+sensor+" is: "+valueb);
		System.out.println("The mean value of NO2 in "+sensor+" is: "+valuec);
		System.out.println("The mean value of PM10 in "+sensor+" is: "+valued);

	}

	public static void main(String[] args) throws Exception {

		// ********** open files **********
		String sensorsPath = "./src/data/Sensors.csv";
		Scanner sensors = new Scanner(new File(sensorsPath));

		String attributesPath = "./src/data/AttributeType.csv";
		Scanner attributes = new Scanner(new File(attributesPath));

		String dataPath = "./src/data/data.csv";
		Scanner data = new Scanner(new File(dataPath));

		// ********** main **********

		Calculate cal = new Calculate();
		
		
		System.out.println("Introduce the sensor you want to know the attribute: ");		
		Scanner ent = new Scanner(System.in);
		String numsensor=ent.nextLine();
		
		System.out.println("Introduce the attribute you want to know the values: ");		
		String numatrib=ent.nextLine();
		
		
		double meanValue = cal.meanValueLocationAttribute("Sensor"+numsensor, numatrib);
		System.out.println(meanValue);
		
		System.out.println("Introduce the latitude you want to know the values: ");		
		String numlongitude=ent.nextLine();
		
		System.out.println("Introduce the longitude you want to know the values: ");		
		String numlatitude=ent.nextLine();
		
		String sensorchoose=sensorLocation(numlatitude, numlongitude);
		sensorValues(sensorchoose);
		
		// ********** close files **********
		sensors.close();
		attributes.close();
		data.close();

	}
}
