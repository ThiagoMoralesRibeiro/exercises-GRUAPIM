import java.util.Scanner;

public class App {

  private static final String[] unit = { "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove" };
  private static final String[] someDecimal = { "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove" };
  private static final String[] decimal = { "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
  private static final String[] cent = { "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };

  public static int setNumber() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite um numero inteiro entre 0 e 999.999.999: ");
    
    int number = scanner.nextInt();
    
    if (number < 0 || number > 999999999) {
      System.out.println("Erro: Número fora do intervalo permitido!");
      System.exit(1);
    }

    return number;
  }

  public static String convertUnit(int num) {
    return unit[num];
  }

  public static String convertDecimal(int dec, int num) {
    if (dec == 1) {
      return someDecimal[num];
    }
    String result = decimal[dec];
    if (num > 0) {
      result += " e " + convertUnit(num);
    }
    return result;
  }

  public static String convertCent(int cen, int dec, int num) {
    if (cen == 1 && dec == 0 && num == 0) {
      return "cem";
    }
    
    String result = cent[cen];

    if (dec > 0 || num > 0) {
      result += " e " + convertDecimal(dec, num);
    }

    return result;
  }

  public static String convertNumber(int number) {
    int cent = number / 100;
    int dec = (number % 100) / 10;
    int num = number % 10;

    if (cent > 0) {
      return convertCent(cent, dec, num);
    } else if (dec > 0) {
      return convertDecimal(dec, num);
    } else {
      return convertUnit(num);
    }
  }

  public static String convertThousand(int number) {
    int thousand = number / 1000;
    int rest = number % 1000;
    String result;

    if (thousand == 1) {
      result = "mil";
    } else {
      result = convertNumber(thousand) + " mil";
    }

    if (rest > 0) {
      result += " e " + convertNumber(rest);
    }
    return result;
  }

  public static String convertMillion(int number) {
    int million = number / 1000000;
    int rest = number % 1000000;
    String result;

    if (million == 1) {
      result = "um milhão";
    } else {
      result = convertNumber(million) + " milhões";
    }

    if (rest > 0) {
      result += " e " + convertThousand(rest);
    }
    return result;
  }

  public static String writeNumber(int number) {
    if (number == 0) {
      return "zero";
    } else if (number < 1000) {
      return convertNumber(number);
    } else if (number < 1000000) {
      return convertThousand(number);
    } else {
      return convertMillion(number);
    }
  }

  public static void main(String[] args) {
    int number = setNumber();
    System.out.println("Numero por extenso: " + writeNumber(number));
  }
}
