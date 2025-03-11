
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    double a = 0, b = 0, c = 0;

    try {
      System.out.print("Digite o primeiro número: ");
      a = scanner.nextDouble();

      System.out.print("Digite o segundo número: ");
      b = scanner.nextDouble();

      System.out.print("Digite o terceiro número: ");
      c = scanner.nextDouble();

    } catch (Exception e) {
      System.out.println("SOMENTE NUMEROS, USUARIO!"); 
    }

    double lower, half, bigger;

    if (a <= b && a <= c) {
      lower = a;
      if (b <= c) {
        half = b;
        bigger = c;
      } else {
        half = c;
        bigger = b;
      }
    } else if (b <= a && b <= c) {
      lower = b;
      if (a <= c) {
        half = a;
        bigger = c;
      } else {
        half = c;
        bigger = a;
      }
    } else {
      lower = c;
      if (a <= b) {
        half = a;
        bigger = b;
      } else {
        half = b;
        bigger = a;
      }
    }

    System.out.println("Os números em ordem crescente: " + lower + ", " + half + ", " + bigger);

    scanner.close();
  }
}
