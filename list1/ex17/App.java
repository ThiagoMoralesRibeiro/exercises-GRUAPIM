import java.util.Scanner;

public class App {

  public static int setNumber() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Digite um numero inteiro (1-999): ");
    int number = scanner.nextInt();
    if (number < 1 || number > 999) {
      System.out.println("Erro: Não passe esses valores!");
      System.exit(1);
    }
    scanner.close();

    return number;
  }

  public static String convertUnit(int unit) {
    String[] characters = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
    return characters[unit];
  }

  public static String convertDecimal(int decimal) {
    String[] characters = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    return characters[decimal];
  }

  public static String convertCent(int cent) {
    String[] characters = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    return characters[cent];
  }

  public static String convertCharacters(int number) {
    int cent = number / 100;
    int decimal = (number % 100) / 10;
    int unit = number % 10;

    return convertCent(cent) + convertDecimal(decimal) + convertUnit(unit);
  }

  public static void main(String[] args) {
    int number = setNumber();
    String characters = convertCharacters(number);
    System.out.println("Numero " + number + " em romano: " + characters);
  }
}
