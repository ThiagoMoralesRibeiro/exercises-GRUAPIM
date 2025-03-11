import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Digite um número inteiro: ");
            n = scanner.nextInt();

            if (n < 50) {
                System.out.println("O número " + n + " é menor do que 50");
            }
        } while (n < 50); 

        System.out.println("O número " + n + " digitado é maior ou igual a 50");
        scanner.close();
    }
}

 
