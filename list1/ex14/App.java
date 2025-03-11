import java.util.Random;

public class App {
  public static int[] arr = new int[50];
  public static int[] arr2 = new int[50];

  public static int[] mergingArrays(int[] arr, int[] arr2){
    int merged[] = new int[arr.length + arr2.length];
    int i = 0, j = 0, k = 0;

    while (i < arr.length && j < arr2.length) {
      if (arr[i] < arr2[j]) {
        merged[k++] = arr[i++];
      } else {
        merged[k++] = arr2[j++];
      }
    }

    while (i < arr.length) {
      merged[k++] = arr[i++];
    }

    while (j < arr2.length) {
      merged[k++] = arr2[j++];
    }

    return merged;
  }

  public static void main(String[] args) {
    Random generator = new Random();
    int aux = 0;

    for (int i = 0; i < arr.length; i++) {
      arr[i] = generator.nextInt(1000);
      arr2[i] = generator.nextInt(1000);
      
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


    for (int i = 0; i < arr2.length; i++) {
      for (int j = 0; j < arr2.length; j++) {
        if(arr2[i] < arr2[j]){
          aux = arr2[i];
          arr2[i] = arr2[j];
          arr2[j] = aux;
        }
      }
    }


    int[] mergedArr = mergingArrays(arr, arr2);


    for (int k = 0; k < mergedArr.length; k++) {
      System.out.println(mergedArr[k]);
    }
  }


}
