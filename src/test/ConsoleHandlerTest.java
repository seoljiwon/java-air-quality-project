import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConsoleHandlerTest {

    private ConsoleHandler consoleHandler;
    List<Sensor> sensors = new ArrayList<>();

    @Before
    public void setUp() throws FileNotFoundException {
        consoleHandler = new ConsoleHandler();
        sensors.add(new Sensor("a", 10, -10));
        sensors.add(new Sensor("a", 0, 20));
    }

    @Test
    public void getNearSensorTest() {
        Sensor s = consoleHandler.getNearSensor(10, -10, sensors);
        assertEquals(sensors.get(0), s);
    }
}