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

        System.out.println("Enter a date (integers expected)");
        int month = 0, day = 0;

        while (month < 1 || month > 12) {
            System.out.println("month");
            month = scanner.nextInt();

            if (month < 1 || month > 12) {
                System.out.println("Wrong arguments.\nPlease type again.\n");
            }
        }

        while (day < 1 || day > 31) {
            System.out.println("day");
            day = scanner.nextInt();

            if (day < 1 || day > 31) {
                System.out.println("Wrong arguments.\nPlease type again.\n");
            }
        }

        // First timestamp: 2017-01-01T00:01:20.6090000
        // Last timestamp:  2017-12-30T23:30:10.9830000

        String date = month < 10 ? "2017-0" + month : "2017-" + month;
        date += day < 10 ? "-0" + day : "-" + day;

        return date;
    }

    public Attribute getAttribute() {
        Attribute attribute = null;
        while (attribute == null) {
            System.out.println("Enter a attribute {O3, SO2, NO2, PM10}");
            String attributeId = scanner.next();

            attribute = fLoader.getAttribute(attributeId);

            if (attribute == null) {
                System.out.println("Wrong arguments.\nPlease type again.\n");
            }
        }
        return attribute;
    }

    public Sensor getSensor() {
        Sensor sensor = null;
        while (sensor == null) {
            System.out.println("Enter a location \nlatitude");
            double latitude = scanner.nextDouble();

            System.out.println("longitude");
            double longitude = scanner.nextDouble();

            sensor = getNearSensor(latitude, longitude, fLoader.sensors);
            if (sensor == null) {
                System.out.println("Wrong arguments.\nPlease type again.\n");
            }
        }
        return sensor;
    }

    public void printAllAttribute(Sensor sensor, String date) throws Exception {
        for (Attribute attribute : fLoader.attributes) {
            printAttribute(sensor, attribute, date);
        }
    }

    public void printAttribute(Sensor sensor, Attribute attribute, String date) throws Exception {
        double average = cal.getAverageBySensorDate(sensor.id, attribute.id, date);
        System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") " + ": " + average + " " + attribute.unit + ", " + airQuality.getAirQuality(average));
    }
}
