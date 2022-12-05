import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Calculator cal = new Calculator();
        FileLoader fLoader = new FileLoader();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a location \nlatitude");
        double latitude = scanner.nextDouble();

        System.out.println("longitude");
        double longitude = scanner.nextDouble();

        System.out.println("Enter a attribute");
        String attributeId = scanner.next();

        Sensor sensor = cal.getNearSensor(latitude, longitude, fLoader.sensors);
        Attribute attribute = fLoader.getAttribute(attributeId);

        if (sensor != null && attribute != null) {
            double average = cal.getAverageBySensorAttribute(sensor.id, attribute.id);
            System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") in " + sensor.id + ": " + average + " " + attribute.unit + "\n----------");
        } else {
            System.out.println("Wrong arguments.");
        }
    }
}