import java.util.Scanner;

public class App {

  public static int setSeconds() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite o valor do tempo em segundos: ");
    int seconds = scanner.nextInt();
    if (seconds < 0) {

      System.out.println("Erro: Não passe valores negativos!");
      System.exit(1); 
    }
    scanner.close();

    return seconds;
  }

  public static int calcHours(int seconds) {
    return seconds / 3600;
  }

  public static int calcMinutes(int seconds) {
    return (seconds % 3600) / 60;
  }

  public static int calcSeconds(int seconds) {
    return seconds % 60;
  }

  public static void show(int hours, int minutes, int seconds) {
    System.out.println("Tempo: " + hours + ":" + minutes + ":" + seconds);
  }

  public static void main(String[] args) {
    int totalSeconds = setSeconds();

    int hours = calcHours(totalSeconds);
    int minutes = calcMinutes(totalSeconds);
    int seconds = calcSeconds(totalSeconds);

    show(hours, minutes, seconds);
  }
}
