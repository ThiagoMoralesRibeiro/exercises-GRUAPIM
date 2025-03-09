import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    double ca, co, hip;
    
    System.out.println("Digite os valores de p1 e p2");
        
    ca = keyboard.nextDouble();
    co = keyboard.nextDouble();
    

    hip = Math.sqrt(Math.pow(ca, 2) + Math.pow(co, 2));

    System.out.println("A distancia entre os dois pontos: " + hip);
 
    
    keyboard.close();
  }
}
