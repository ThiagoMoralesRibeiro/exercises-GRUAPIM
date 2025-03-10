public class App {
  public static void main(String[] args) {
    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new MeuRunnable());
      threads[i].start();
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].join();
    }

    /*Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new MeuRunnable());
      threads[i].start();
      threads[i].join();
    }
    */
  }
}
