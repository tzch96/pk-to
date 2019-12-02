public class Ctg implements SingleArgFunction {
  public Double calculate(Double x) {
    return 1.0 / Math.tan(Math.toRadians(x));
  }
}
