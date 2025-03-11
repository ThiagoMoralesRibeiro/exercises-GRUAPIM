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

    //No primeiro programa estamos lidando com a execução de 10 threads simultaneas. São criadas 10 threads e cada uma delas são iniciadas,
    //contudo o programa nao espera que cada uma delas termine imediatamente, depois que foram lançadas. Após iniciar cada uma dessas threads
    //o programa espera elas terminarem para uní-las, como pode ser visto no segundo loop

    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new MeuRunnable());
      threads[i].start();
      threads[i].join();
    }
    
    //No segundo estamos lidando com um programa que cria 10 threads e as inicia. Após iniciar uma thread, o programa espera que ela
    //termine para poder executar a proxima, indiciando que as thread serao executadas em sequencia.
    
    //O programa A acaba sendo melhor por se aproveitar do paralelismo real. Vale ressaltar que comumente estamos trabalhando com
    //computadores que possuem varios nucleos de CPU, permitindo que as threads sejam distribuídas entre eles e realmente usufruindo
    //de forma eficiente do recurso computacional.
  }
}
