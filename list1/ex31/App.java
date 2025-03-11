import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    double a, b, c, delta, x1, x2;

    System.out.println("Digite os valores de A, B e C");

    try {
      a = keyboard.nextDouble();
      b = keyboard.nextDouble();
      c = keyboard.nextDouble();

    } catch (InputMismatchException e) {
      System.out.println("Erro: Por favor, insira apenas números.");
      return;
    }

    if (a == 0) {
      System.out.println("Erro: O valor de A deve ser diferente de zero.");
      return;
    }

    delta = (b * b) - (4 * a * c);

    if (delta < 0) {
      System.out.println("Impossivel de se calcular");
    } else {
      x1 = (-b + Math.sqrt(delta)) / (2 * a);
      x2 = (-b - Math.sqrt(delta)) / (2 * a);

      System.out.println("R1 = " + x1);
      System.out.println("R2 = " + x2);
    }

    keyboard.close();
  }
}
