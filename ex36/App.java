
import java.util.Scanner;

class Primo extends Thread {
  private int start;
  private int end;
  private int count;

  public Primo(int start, int end) {
    this.start = start;
    this.end = end;
    this.count = 0;
  }

  public int getCount() {
    return count;
  }

  @Override
  public void run() {
    for (int i = start; i <= end; i++) { 
      if (isPrimo(i)) {
        count++;
      }
    }
  }

  public boolean isPrimo(int number) {
    if (number <= 1) {
      return false;
    }

    
    for (int i = 2; i <= Math.sqrt(number); i++) { 
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
}

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Informe o número n: ");
    int n = scanner.nextInt();

    // Dividindo o trabalho entre duas threads
    int half = n / 2;

    // Criação das threads
    Primo thread1 = new Primo(0, half);
    Primo thread2 = new Primo(half + 1, n);

    // Inicia as threads
    thread1.start();
    thread2.start();

    try {
      // Aguarda as threads terminarem
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Soma os resultados das duas threads
    int totalPrimo = thread1.getCount() + thread2.getCount();

    System.out.println("Quantidade de números primos entre 0 e " + n + ": " + totalPrimo);

    scanner.close();
  }
}
