import java.util.InputMismatchException;

public class AirQuality {
    int good = 50;
    int satisfactory = 100;
    int moderate = 200;
    int poor = 300;
    int veryPoor = 400;
    int severe = 500;

    public String getAirQuality(double value) {
        if (value >= 0 && value <= good) {
            return "good";
        } else if (value > good && value <= satisfactory) {
            return "satisfactory";
        } else if (value > satisfactory && value <= moderate) {
            return "moderate";
        } else if (value > moderate && value <= poor) {
            return "poor";
        } else if (value > poor && value <= veryPoor) {
            return "veryPoor";
        } else if (value > veryPoor && value <= severe) {
            return "severe";
        } else {
            throw new InputMismatchException("out of value");
        }
    }
}
