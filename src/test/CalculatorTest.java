import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test(expected = ArithmeticException.class)
    public void getAverageBySensorDateExceptionTest() throws FileNotFoundException {
        calculator.getAverageBySensorDate("sensor", "attribute", "221212");
    }
}