import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    double n = 0;
    Scanner scanner = new Scanner(System.in);
    
    try {
      System.out.println("Digite um valor real para ser convertido (m para pés): ");
      n = scanner.nextDouble();
    } catch (Exception e) {
      System.out.println("FAVOR, PASSAR UM NUMERO REAL!");
      return;
    }

    if(n < 0){
      System.out.println("Não é possível calcular uma medida negativa!");
      return;
    }

    double foot = n * 3315;

    System.out.println("O valor em metros (" + n + ") convertido para pés: " + foot);
        

  }
}
