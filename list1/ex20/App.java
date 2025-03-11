import java.util.ArrayList;
import java.util.Scanner;

class Schedule {
  private ArrayList<Contact> scheduleList;

  public Schedule() {
    this.scheduleList = new ArrayList<>();
  }

  public void addContact(String name, String phone) {
    scheduleList.add(new Contact(name, phone));
    System.out.println("Contato adicionado com sucesso!");
  }

  public void listContact() {
    if (scheduleList.isEmpty()) {
      System.out.println("A agenda esta vazia");
    } else {
      System.out.println("Lista de Contatos");
      System.out.println("-----------------");
      for (int i = 0; i < scheduleList.size(); i++) {
        System.out.println(scheduleList.get(i));
      }
    }
  }

  public void searchContact(String name) {
    for (int i = 0; i < scheduleList.size(); i++) {
      if (scheduleList.get(i).getName().equalsIgnoreCase(name)) {
        System.out.println("Contato encontrado: " + scheduleList.get(i));
        return;
      }
    }

    System.out.println("Contato nao encontrado");
  }

}

class Contact {
  private String name;
  private String phone;

  public Contact(String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Nome: " + name + ", Telefone: " + phone;
  }
}

public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Schedule schedule = new Schedule();

    while (true) {
      System.out.println("\n=== AGENDA TELEFÔNICA ===");
      System.out.println("1 - Adicionar Contato");
      System.out.println("2 - Listar Contatos");
      System.out.println("3 - Buscar Contato");
      System.out.println("4 - Sair");
      System.out.print("Escolha uma opção: ");

      int option = scan.nextInt();
      scan.nextLine();

      switch (option) {
        case 1:
          System.out.print("Nome: ");
          String name = scan.nextLine();
          System.out.print("Telefone: ");
          String phone = scan.nextLine();
          schedule.addContact(name, phone);
          break;
        case 2:
          schedule.listContact();
          break;
        case 3:
          System.out.print("Digite o nome do contato: ");
          String search = scan.nextLine();
          schedule.searchContact(search);
          break;
        case 4:
          System.out.println("Saindo da agenda. Até logo!");
          scan.close();
          return;
        default:
          System.out.println("Opção inválida. Tente novamente.");
      }
    }
  }
}
