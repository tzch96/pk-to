public class Cos implements SingleArgFunction {
    public Double calculate(Double x) {
        return Math.cos(Math.toRadians(x));
    }
}