import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        ConsoleHandler consoleHandler = new ConsoleHandler();

        Scanner scanner = new Scanner(System.in);

        Sensor sensor;

        System.out.println("Enter option");
        System.out.println("1. Get air quality of all attributes for a specific location");
        System.out.println("2. Get air quality of all attributes for a specific location and date");
        System.out.println("3. Get air quality of a specific attribute for a specific location");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                sensor = consoleHandler.getSensor();

                System.out.println("\nNear sensor is " + sensor.id);

                consoleHandler.printAllAttribute(sensor, null);
                break;
            case 2:
                sensor = consoleHandler.getSensor();
                String date = consoleHandler.getDate();

                System.out.println("\nNear sensor is " + sensor.id);
                System.out.println("Data on " + date);

                consoleHandler.printAllAttribute(sensor, date);
                break;
            case 3:
                sensor = consoleHandler.getSensor();
                Attribute attribute = consoleHandler.getAttribute();

                System.out.println("Near sensor is " + sensor.id);

                consoleHandler.printAttribute(sensor, attribute, null);
                break;
            default:
                System.out.println("Wrong option.");
        }
    }
}