import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    double P0, V, A;
    int T;

    try {
      System.out.print("Digite a posição inicial (P0): ");
      P0 = scanner.nextDouble();

      System.out.print("Digite a velocidade do móvel (V): ");
      V = scanner.nextDouble();

      System.out.print("Digite a aceleração do móvel (A): ");
      A = scanner.nextDouble();

      System.out.print("Digite o tempo decorrido (T): ");
      T = scanner.nextInt();

    } catch (Exception e) {
      System.out.println("VALOR INCONGRUENTE!");
      return;
    }

    double PF = P0 + (V * T) + (A * Math.pow(T, 2)) / 2;

    System.out.println("A posição final do móvel é: " + PF);

    scanner.close();
  }
}
