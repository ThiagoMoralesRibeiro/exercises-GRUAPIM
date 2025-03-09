/*import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int codigo;
    System.out.println("Informe o código: ");
    codigo = teclado.nextInt();
    while (codigo != -1) {
      System.out.println("Código: " + codigo);
      System.out.println("Informe o código: ");
      codigo = teclado.nextInt();
    }
  }
}*/

//A solucao correta seria a do Programa A

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int codigo;
    do {
      System.out.print("Informe o código: ");
      codigo = teclado.nextInt();
      if (codigo != -1) {
        System.out.println("Código: " + codigo);

      }
    } while (codigo != -1);
  }
}


//Arrumado
