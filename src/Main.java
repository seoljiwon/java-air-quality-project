import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // ********** open files **********
        String sensorsPath = "./src/data/Sensors.csv";
        Scanner sensors = new Scanner(new File(sensorsPath));

        String attributesPath = "./src/data/AttributeType.csv";
        Scanner attributes = new Scanner(new File(attributesPath));

        String dataPath = "./src/data/data.csv";
        Scanner data = new Scanner(new File(dataPath));

        // ********** main **********

        // mean value in a specific location of a specific attribute

        String location = "Sensor0";
        String attr = "O3";

        int counter = 0;
        int sum = 0;
        while (data.hasNext()) {
            String[] columns = data.next().split(";");
            if (columns[1].equals(location) && columns[2].equals(attr)) {
                sum += Double.parseDouble(columns[3]);
                counter++;
            }
        }

        if (counter != 0) {
            String unit = "";
            String description = "";
            boolean found = false;
            while (attributes.hasNext() && !found) {
                String[] columns = attributes.next().split(";");
                if (columns[0].equals(attr)) {
                    found = true;
                    unit = columns[1];
                    description = columns[2];
                }
            }
            System.out.println("----------\nMean " + description + " (" + attr + ") in " + location + ": " + sum / counter + " " + unit + "\n----------");
        }
        else System.out.println("Not found.");


        // ********** close files **********
        sensors.close();
        attributes.close();
        data.close();

    }
}