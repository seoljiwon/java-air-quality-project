import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;

public class Calculator {

    public double getAverageBySensorDate(String sensorId, String attributeId, String date) throws FileNotFoundException {
        // calculates the mean value of a given attribute (chemical formula) and a given location (sensor)
        // on a specific day, optional

        // open files
        String dataPath = "./data/data.csv";
        Scanner data = new Scanner(new File(dataPath));

        // calculate value

        int counter = 0;
        double sum = 0;
        while (data.hasNext()) {
            String[] columns = data.next().split(";");

            if (columns[1].equals(sensorId) && columns[2].equals(attributeId) || (data == null || date != null && date.equals(columns[0].split("T")[0]))) {
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
}
