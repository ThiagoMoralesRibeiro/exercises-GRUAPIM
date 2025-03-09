import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    int num, first = 0, second = 1, next;
    Scanner keyboard = new Scanner(System.in);
    String number;

    System.out.print("Digite um número: ");
    num = keyboard.nextInt();

    if (num < 0) {
      System.out.print("Impossivel de calcular a sequencia de fibonacci");
    } else {
      System.out.println("Fibonacci Sequence:");
      for (int i = 0; i < num; i++) {
        System.out.println(first);

        next = first + second;
        first = second;
        second = next; 
      }

    }
 
    keyboard.close();

  }
}
