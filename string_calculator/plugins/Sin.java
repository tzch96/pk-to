public class Sin implements SingleArgFunction {
    public Double calculate(Double x) {
        return Math.sin(Math.toRadians(x));
    }
}