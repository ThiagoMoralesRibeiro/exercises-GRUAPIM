import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);
    String number;
    int num, count = 0;
    
    System.out.print("Digite um número: ");
    num = keyboard.nextInt();

    number = String.valueOf(num);

    char[] digits = number.toCharArray();

    //Poderia ter pego apenas o tamanho sem precisar do loop
    //number = String.valueOf(num);
    //int quantidadeDigitos = number.length();

    for (int i = 0; i < digits.length; i++) {
      count++; 
    }

    System.out.println("A quantidade de digitos do numero (" + number + "): " + count);

    keyboard.close();

  }
}
