import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    double a, b, c, delta, x1, x2;

    System.out.println("Digite os valores de A, B e C");
        
    a = keyboard.nextDouble();
    b = keyboard.nextDouble();
    c = keyboard.nextDouble();

    delta = (b*b) - (4 * a * c);

    
    if(delta <= 0 || a == 0 || b == 0 || c == 0){
      System.out.println("A:" + a);
      System.out.println("B:" + b);
      System.out.println("C:" + c);

      System.out.println("Impossivel de se calcular");
    }else{
      x1 = (-b + Math.sqrt(delta)) / (2 * a);
      x2 = (-b - Math.sqrt(delta)) / (2 * a);

      System.out.println("R1 = " + x1);
      System.out.println("R2 = " + x2);
    }
    
    keyboard.close();
  }
}
