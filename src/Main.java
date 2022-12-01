import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // ********** open files **********
        String sensorsPath = "./src/data/Sensors.csv";
        Scanner sensors = new Scanner(new File(sensorsPath));

        String attributesPath = "./src/data/AttributeType.csv";
        Scanner attributes = new Scanner(new File(attributesPath));

        String dataPath = "./src/data/data_new.csv";
        Scanner data = new Scanner(new File(dataPath));

        // ********** main **********

        // mean value in a specific location of a specific attribute

        String location = "Sensor0";
        String attr = "O3";

        int counter = 0;
        int sum = 0;
        System.out.println(data.next());
        while (data.hasNext()) {
            String[] columns = data.next().split(";");
            // System.out.println(columns[1]);
            // System.out.println(columns[2]);
            if (columns[1] == location && columns[2] == attr) {
                sum += Double.parseDouble(columns[3]);
                counter++;
            }
        }
        System.out.println(counter);
        if (counter != 0)
            System.out.println(sum/counter);


        // ********** close files **********
        sensors.close();
        attributes.close();
        data.close();

    }
}