public class App {
  public static void main(String[] args) {

    System.out.println("==== Tabuada de 1 a 10 ====\n");

    for (int i = 1; i <= 10; i++) {
      System.out.println("Tabuada do " + i);
      System.out.println("-----------------");
      for (int j = 0; j <= 10; j++) {
        System.out.println(i + " * " + j + " = " + i * j);
      }
      System.out.println();
    }
  }
}
