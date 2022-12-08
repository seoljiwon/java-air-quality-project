import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class Calculator {
    public Sensor getNearSensor(double latitude, double longitude, List<Sensor> sensors) {
        // calculates the near sensor for a specific location
        Sensor nearSensor = null;
        double minDist = 999999;
        for (Sensor sensor : sensors) {
            if ((latitude == sensor.latitude) && (longitude == sensor.longitude)) {
                return sensor;
            } else {
                double theta = longitude - sensor.longitude;
                double dist = Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(sensor.latitude)) + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(sensor.latitude)) * Math.cos(Math.toRadians(theta));
                dist = Math.acos(dist);
                dist = Math.toDegrees(dist);
                dist = dist * 60 * 1.1515;

                if (dist < minDist) {
                    minDist = dist;
                    nearSensor = sensor;
                }
            }
        }
        return nearSensor;
    }

    public double getAverageBySensorAttribute(String sensorId, String attributeId) throws FileNotFoundException {
        // calculates the mean value of a given attribute (chemical formula) for a specific location (sensor)
        // returns the mean value

        // open files
        String dataPath = "./src/data/data.csv";
        Scanner data = new Scanner(new File(dataPath));

        // calculate value
        int counter = 0;
        double sum = 0;
        while (data.hasNext()) {
            String[] columns = data.next().split(";");
            if (columns[1].equals(sensorId) && columns[2].equals(attributeId)) {
                sum += Double.parseDouble(columns[3]);
                counter++;
            }
        }

        if (counter == 0) {
            throw new ArithmeticException("divide by zero");
        }

        // close files
        data.close();

        return sum / counter;
    }

    public double getAverageBySensorDay(String sensorId, String attributeId, String date) throws FileNotFoundException {
        // calculates the mean value of a given attribute (chemical formula) and a given location (sensor)
        // on a specific day

        // open files
        String dataPath = "./src/data/data.csv";
        Scanner data = new Scanner(new File(dataPath));

        // calculate value

        // First timestamp: 2017-01-01T00:01:20.6090000
        // Last timestamp:  2017-12-30T23:30:10.9830000

        int counter = 0;
        double sum = 0;
        while (data.hasNext()) {
            String[] columns = data.next().split(";");

            if (columns[1].equals(sensorId) && columns[2].equals(attributeId) && date.equals(columns[0].split("T")[0])) {
                sum += Double.parseDouble(columns[3]);
                counter++;
            }
        }

        if (counter == 0) {
            throw new ArithmeticException("divide by zero");
        }

        // close files
        data.close();

        return sum / counter;
    }

    String getDate(int month, int day) {
        // returns the date in a correct format

        String date;
        if (month < 10) {
            if (day < 10)
                date = "2017-0" + Integer.toString(month) + "-0" + Integer.toString(day);
            else date = "2017-0" + Integer.toString(month) + "-" + Integer.toString(day);
        }
        else {
            if (day < 10)
                date = "2017-" + Integer.toString(month) + "-0" + Integer.toString(day);
            else date = "2017-" + Integer.toString(month) + "-" + Integer.toString(day);
        }
        return date;
    }
}
