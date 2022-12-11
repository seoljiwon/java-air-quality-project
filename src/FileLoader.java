import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    List<Attribute> attributes = new ArrayList<>();
    List<Sensor> sensors = new ArrayList<>();

    public Attribute getAttribute(String id) {
        for (Attribute attribute : attributes) {
            if (attribute.id.equalsIgnoreCase(id)) {
                return attribute;
            }
        }
        return null;
    }

    public void readAttributeFile() throws FileNotFoundException {
        String attributeFilePath = "./data/AttributeType.csv";
        Scanner attributeFile = new Scanner(new File(attributeFilePath));

        attributeFile.nextLine();
        while (attributeFile.hasNextLine()) {
            String[] columns = attributeFile.nextLine().split(";");
            String id = columns[0];
            String unit = columns[1];
            String description = columns[2];

            Attribute attribute = new Attribute(id, unit, description);
            attributes.add(attribute);
        }
        attributeFile.close();
    }

    public void readSensorFile() throws FileNotFoundException {
        String sensorFilePath = "./data/Sensors.csv";
        Scanner sensorFile = new Scanner(new File(sensorFilePath));

        sensorFile.nextLine();
        while (sensorFile.hasNextLine()) {
            String[] columns = sensorFile.nextLine().split(";");
            String id = columns[0];
            double latitude = Double.parseDouble(columns[1]);
            double longitude = Double.parseDouble(columns[2]);

            Sensor sensor = new Sensor(id, latitude, longitude);
            sensors.add(sensor);
        }
        sensorFile.close();
    }


    public FileLoader() throws FileNotFoundException {
        readAttributeFile();
        readSensorFile();
    }

}
