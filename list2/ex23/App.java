
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    double pv, tax;
    int period;

    try {
      System.out.print("Digite o valor do capital inicial (PV): ");
      pv = scanner.nextDouble();

      System.out.print("Digite o valor da taxa de juros (J): ");
      tax = scanner.nextDouble();

      System.out.print("Digite o período de aplicação (N): ");
      period = scanner.nextInt();
    } catch (Exception e) {
      System.out.println("VALOR INCONGRUENTE!");
      scanner.close();
      return;
    }

    
    double fv = pv * Math.pow((1 + tax), period);

    
    System.out.printf("O valor do capital futuro é de: R$%.2f%n", fv);

    scanner.close();
  }
}

