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

        Calculate cal = new Calculate();

        double meanValue = cal.meanValueLocationAttribute("Sensor0", "O3");
        System.out.println(meanValue);




        // ********** close files **********
        sensors.close();
        attributes.close();
        data.close();

    }
}