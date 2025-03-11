import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class App {
  public static ArrayList<Integer> arr = new ArrayList<>();

  public static void main(String[] args) {
    Random generator = new Random();

    for (int i = 0; i < 100; i++){
      arr.add(generator.nextInt(1000));
    }
  
    Collections.sort(arr);

    for (int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i));
    }
  
  }


}
