import java.util.Scanner;

public class App {

  public static float calculateTax(float balance, float value, float interestRate){
    float interestAmount;

    balance += value;
    interestAmount = balance * interestRate;
    balance += interestAmount;

    return balance;

  }

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    String option = "";

    float value, interestRate, balance = 0;

    System.out.println("Valor que sera investido por mes (R$): ");
    value = keyboard.nextFloat();

    System.out.println("Taxa de juros (%): ");
    interestRate = keyboard.nextFloat();

    interestRate = interestRate / 100;

    for (int i = 0; i < 12; i++) {
      balance = calculateTax(balance, value, interestRate); 
    }

    System.out.printf("\nSaldo total após 1 ano: R$ %.2f%n", balance);

    while (true) {
      System.out.print("\nDeseja processar mais um mes? (Y/N): ");
      option = keyboard.next().trim();

      if (option.equalsIgnoreCase("N")) {
        break; 
      }

      balance = calculateTax(balance, value ,interestRate);

      System.out.printf("Saldo apos mais um mes: R$ %.2f\n", balance) ;
    }
    keyboard.close();
  }
}
