import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Digite um número inteiro: ");
            n = scanner.nextInt();

            if (n < 100) {
                System.out.println("O número " + n + " é menor do que 100");
            }
        } while (n < 100); 

        System.out.println("O número " + n + " digitado é maior ou igual a 100");
        scanner.close();
    }
}


