import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double a = 0, b = 0, c = 0;

    try {
      System.out.print("Digite um número inteiro: ");
      a = scanner.nextDouble();
      System.out.print("Digite um número inteiro: ");
      b = scanner.nextDouble();
      System.out.print("Digite um número inteiro: ");
      c = scanner.nextDouble();

    } catch (InputMismatchException e) {
      System.out.println("O numero deve ser real");
      return;
    }

    if (a < b && a < c) {
      System.out.println("O numero " + a + " é o menor dentre eles!");
    } else if (b < a && b < c) {
      System.out.println("O numero " + b + " é o menor dentre eles!");
    } else {
      System.out.println("O numero " + c + " é o menor dentre eles!");

    }

    scanner.close();

  }
}
