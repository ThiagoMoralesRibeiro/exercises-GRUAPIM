public class App {

  public static double calcMean(double[] score) {
    double sum = 0;
    for (int i = 0; i < score.length; i++) {
      sum += score[i];
    }
    return sum / score.length;
  }

  public static String status(double mean) {
    if (mean > 6) {
      return "Aprovado";
    } else if (mean >= 4) {
      return "Verificação Suplementar";
    } else {
      return "Reprovado";
    }
  }

  public static void main(String[] args) {
    double[] scores = {8.0, 9.0, 6.0};

    double mean = calcMean(scores);
    String status = status(mean);

    System.out.printf("Media do aluno: %.2f\n", mean);
    System.out.println("Status: " + status);

  


  }
}
