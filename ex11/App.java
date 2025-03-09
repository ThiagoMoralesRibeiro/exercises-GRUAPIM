
import java.util.Scanner;

public class App {

  public static float aproximatedSqrt(float number, float maximumError) {
    if (number < 0) {
      throw new IllegalArgumentException("Impossível calcular a raiz quadrada de um número negativo");
    }

    float approach = number / 2; // 

    while (Math.abs(approach * approach - number) > maximumError) {
      approach = (approach + number / approach) / 2;     
    }

    return approach;   
  }

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.print("Número inteiro: ");
    float number = keyboard.nextFloat();

    System.out.print("Informe o erro máximo que será permitido: ");
    float error = keyboard.nextFloat();

    try {
      System.out.printf("A raiz quadrada aproximada de %.2f é %.6f%n", number, aproximatedSqrt(number, error));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    keyboard.close();
  }
}

 

 
      

    
