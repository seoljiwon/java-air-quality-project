public class AirQuality {
    int good = 50;
    int satisfactory = 100;
    int moderate = 200;
    int poor = 300;
    int veryPoor = 400;
    int severe = 500;

    public String getAirQuality(double value) throws Exception {
        if (value >= 0 && value <= good) {
            return "good";
        } else if (value <= satisfactory) {
            return "satisfactory";
        } else if (value <= moderate) {
            return "moderate";
        } else if (value <= poor) {
            return "poor";
        } else if (value <= veryPoor) {
            return "veryPoor";
        } else if (value <= severe) {
            return "severe";
        } else {
            throw new Exception("out of value");
        }
    }
}
