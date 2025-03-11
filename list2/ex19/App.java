
import java.util.Scanner;

public class App {
  private static final String[] unit = { "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito",
      "nove" };
  private static final String[] specialTens = { "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis",
      "dezessete", "dezoito", "dezenove" };
  private static final String[] tens = { "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta",
      "oitenta", "noventa" };

  public static double setNumber() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite um número entre 0 e 100: ");

    double number = scanner.nextDouble();

    if (number < 0 || number > 100) {
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
      return specialTens[num];
    }
    String result = tens[dec];
    if (num > 0) {
      result += " e " + convertUnit(num);
    }
    return result;
  }

  public static String convertNumber(int number) {
    if (number == 100) {
      return "cem";
    }
    int dec = number / 10;
    int num = number % 10;

    if (dec > 0) {
      return convertDecimal(dec, num);
    } else {
      return convertUnit(num);
    }
  }

  public static void main(String[] args) {
    String writeMoney;
    double number = setNumber();
    int real = (int) number;
    int cents = (int) Math.round((number - real) * 100);

    if (real > 0) {
      writeMoney = convertNumber(real) + (real == 1 ? " real" : " reais");
      if (cents > 0) {
        writeMoney += " e " + convertNumber(cents) + " centavo" + (cents > 1 ? "s" : "");
      }
    } else if (cents > 0) {
      writeMoney = convertNumber(cents) + " centavo" + (cents > 1 ? "s" : "");
    } else {
      writeMoney = "zero reais";
    }

    System.out.println("Valor por extenso: " + writeMoney);
  }
}


    

      

      
    
      

      

    
