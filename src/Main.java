public class Main {

    public static void main(String[] args) throws Exception {
        Calculator cal = new Calculator();

        FileLoader fileLoader = new FileLoader();
        Sensor sensor = fileLoader.getSensor("Sensor0");
        Attribute attribute = fileLoader.getAttribute("O3");

        if (sensor != null || attribute != null) {
            double average = cal.getAverageBySensorAttribute(sensor.id, attribute.id);
            System.out.println("----------\nAverage " + attribute.id + " (" + attribute.description + ") in " + sensor.id + ": " + average + " " + attribute.unit + "\n----------");
        } else {
            System.out.println("Wrong arguments. Please type again");
        }
    }
}