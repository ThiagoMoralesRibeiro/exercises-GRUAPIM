import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

  private static final Map<Integer, String> units = new HashMap<>();
  private static final Map<Integer, String> tens = new HashMap<>();
  private static final Map<Integer, String> hundreds = new HashMap<>();

  static {
    units.put(0, "");
    units.put(1, "I");
    units.put(2, "II");
    units.put(3, "III");
    units.put(4, "IV");
    units.put(5, "V");
    units.put(6, "VI");
    units.put(7, "VII");
    units.put(8, "VIII");
    units.put(9, "IX");

    tens.put(0, "");
    tens.put(1, "X");
    tens.put(2, "XX");
    tens.put(3, "XXX");
    tens.put(4, "XL");
    tens.put(5, "L");
    tens.put(6, "LX");
    tens.put(7, "LXX");
    tens.put(8, "LXXX");
    tens.put(9, "XC");

    hundreds.put(0, "");
    hundreds.put(1, "C");
    hundreds.put(2, "CC");
    hundreds.put(3, "CCC");
    hundreds.put(4, "CD");
    hundreds.put(5, "D");
    hundreds.put(6, "DC");
    hundreds.put(7, "DCC");
    hundreds.put(8, "DCCC");
    hundreds.put(9, "CM");
  }

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

  /*public static String convertUnit(int unit) {
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
  */

  public static String convertCharacters(int number) {
    int cent = number / 100;
    int decimal = (number % 100) / 10;
    int unit = number % 10;

    return hundreds.get(cent) + tens.get(decimal) + units.get(unit);
  }

  public static void main(String[] args) {
    int number = setNumber();
    String characters = convertCharacters(number);
    System.out.println("Numero " + number + " em romano: " + characters);
  }
}
