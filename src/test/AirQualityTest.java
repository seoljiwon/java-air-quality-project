import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class AirQualityTest {
    private AirQuality airQuality;

    @Before
    public void setUp() {
        airQuality = new AirQuality();
    }

    @Test
    public void getAirQualityTest() {
        String aq = airQuality.getAirQuality(10);
        assertEquals("good", aq);
    }

    @Test(expected = InputMismatchException.class)
    public void getAirQualityExceptionTest() {
        System.out.println(airQuality.getAirQuality(-1));
    }
}