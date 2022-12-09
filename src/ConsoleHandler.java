import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    Scanner scanner = new Scanner(System.in);
    FileLoader fLoader = new FileLoader();
    Calculator cal = new Calculator();
    AirQuality airQuality = new AirQuality();

    public ConsoleHandler() throws FileNotFoundException {
    }

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

    public String getDate() {
        // returns the date in a correct format

        System.out.println("\nEnter a date (integers expected) \nmonth");
        int month = scanner.nextInt();

        System.out.println("day");
        int day = scanner.nextInt();

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

    public Attribute getAttribute(){
        Attribute attribute = null;
        while (attribute == null) {
            System.out.println("Enter a attribute {O3, SO2, NO2, PM10}");
            String attributeId = scanner.next();

            attribute = fLoader.getAttribute(attributeId);

            if (attribute == null) {
                System.out.println("Wrong arguments. \nPlease type again. \n");
            }
        }
        return attribute;
    }

    public Sensor getSensor(){
        Sensor sensor = null;
        while (sensor == null) {
            System.out.println("Enter a location \nlatitude");
            double latitude = scanner.nextDouble();

            System.out.println("longitude");
            double longitude = scanner.nextDouble();

            sensor = getNearSensor(latitude, longitude, fLoader.sensors);
            if (sensor == null) {
                System.out.println("Wrong arguments. \n Please type again.");
            }
        }
        return sensor;
    }

    public void printAllAttribute (Sensor sensor, String date) throws Exception{
        for (Attribute attribute : fLoader.attributes) {
            printAttribute(sensor, attribute, date);
        }
    }

    public void printAttribute (Sensor sensor, Attribute attribute, String date) throws Exception{
        double average = cal.getAverageBySensorDay(sensor.id, attribute.id, date);
        System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") " + ": " + average + " " + attribute.unit + ", " + airQuality.getAirQuality(average));
    }
}
