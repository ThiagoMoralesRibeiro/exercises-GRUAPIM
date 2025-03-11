import java.util.Scanner;

public class App{
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String name;
        double price, discount = 0, totalValue, discountValue;
        int qtd;
        
        System.out.println("Nome do produto: ");
        name = keyboard.nextLine();
        
        System.out.println("Preco do produto: ");
        price = keyboard.nextDouble();
        
        System.out.println("Quantidade: ");
        qtd = keyboard.nextInt();
        
        
        totalValue = price * qtd;
        
        
        if (qtd > 50) {
            discount = 0.25;
        } else if (qtd > 20) {
            discount = 0.20;
        } else if (qtd > 10) {
            discount = 0.10;
        }
        
        
        discountValue = totalValue * (1 - discount);
        
        System.out.println("\nProduto: " + name);
        System.out.printf("Valor total sem desconto: R$ %.2f%n", totalValue);
        System.out.printf("Desconto aplicado: %.0f%%%n", discount * 100);
        System.out.printf("Valor final a pagar: R$ %.2f%n", discountValue);
        
        keyboard.close();
    }
}
