import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Calculator cal = new Calculator();
        FileLoader fLoader = new FileLoader();
        AirQuality airQuality = new AirQuality();

        Scanner scanner = new Scanner(System.in);

        Sensor sensor = null;

        System.out.println("Enter option");
        System.out.println("1. Get air quality of all attributes for a specific location");
        System.out.println("2. Get air quality of all attributes for a specific location and date");
        System.out.println("3. Get air quality of a specific attribute for a specific location");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                while (sensor == null) {
                    System.out.println("Enter a location \nlatitude");
                    double latitude = scanner.nextDouble();

                    System.out.println("longitude");
                    double longitude = scanner.nextDouble();

                    sensor = cal.getNearSensor(latitude, longitude, fLoader.sensors);
                    if (sensor == null) {
                        System.out.println("Wrong arguments. \n Please type again.");
                    }
                }
                System.out.println("\nNear sensor is " + sensor.id);

                for (Attribute attribute : fLoader.attributes) {
                    double average = cal.getAverageBySensorAttribute(sensor.id, attribute.id);
                    System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") " + ": " + average + " " + attribute.unit + ", " + airQuality.getAirQuality(average));
                }

                break;
            case 2:
                // TODO
                break;
            case 3:
                while (sensor == null) {
                    System.out.println("Enter a location \nlatitude");
                    double latitude = scanner.nextDouble();

                    System.out.println("longitude");
                    double longitude = scanner.nextDouble();

                    sensor = cal.getNearSensor(latitude, longitude, fLoader.sensors);
                    if (sensor == null) {
                        System.out.println("Wrong arguments. \n Please type again.");
                    }
                }

                Attribute attribute = null;
                while (attribute == null) {
                    System.out.println("Enter a attribute");
                    String attributeId = scanner.next();

                    attribute = fLoader.getAttribute(attributeId);

                    if (attribute == null) {
                        System.out.println("Wrong arguments. \nPlease type again. \n");
                    }
                }

                System.out.println("Near sensor is " + sensor.id);

                double average = cal.getAverageBySensorAttribute(sensor.id, attribute.id);
                System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") in " + sensor.id + ": " + average + " " + attribute.unit + ", " + airQuality.getAirQuality(average) + "\n");

                break;
            default:
                System.out.println("Wrong option.");
        }
    }
}