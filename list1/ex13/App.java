import java.util.Random;

public class App {
  public static int[] arr = new int[100];

  public static void main(String[] args) {
    Random generator = new Random();
    int aux = 0;

    for (int i = 0; i < arr.length; i++) {
      arr[i] = generator.nextInt(1000);
      
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if(arr[i] < arr[j]){
          aux = arr[i];
          arr[i] = arr[j];
          arr[j] = aux;
        }
      }
    }

    for (int k = 0; k < arr.length; k++) {
      System.out.println(arr[k]);
    }
  }


}
