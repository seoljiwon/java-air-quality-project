import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Calculate {

    public double meanValueLocationAttribute(String location, String attr) throws FileNotFoundException {

        // calculates the mean value of a given attribute (chemical formula) for a specific location (sensor)
        // returns the mean value or  -1 if wrong arguments

        // open files

        String attributesPath = "./src/data/AttributeType.csv";
        Scanner attributes = new Scanner(new File(attributesPath));

        String dataPath = "./src/data/data.csv";
        Scanner data = new Scanner(new File(dataPath));

        // calculate value

        double counter = 0;
        double sum = 0;
        while (data.hasNext()) {
            String[] columns = data.next().split(";");
            if (columns[1].equals(location) && columns[2].equals(attr)) {
                sum += Double.parseDouble(columns[3]);
                counter++;
            }
        }

        if (counter != 0) {
            String unit = "";
            String description = "";
            boolean found = false;
            while (attributes.hasNext() && !found) {
                String[] columns = attributes.next().split(";");
                if (columns[0].equals(attr)) {
                    found = true;
                    unit = columns[1];
                    description = columns[2];
                }
            }
            System.out.println("----------\nMean " + description + " (" + attr + ") in " + location + ": " + sum / counter + " " + unit + "\n----------");
        }
        else {
            sum = -1 * counter;
            System.out.println("Wrong arguments.");
        }

        // close files
        attributes.close();
        data.close();

        return sum / counter;
    }

    public Calculate() throws FileNotFoundException {
    }


}
