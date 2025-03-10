import java.util.ArrayList;

class Book{
  public String title;
  public String author;
  public boolean borrowed;

  public Book(String title, String author){
    this.title = title;
    this.author = author;
    this.borrowed = false;
  }

  public boolean isBorrowed(){
    return borrowed;
  }

  public void borrow(){
    this.borrowed = true;
  }

  public void takeBack(){
    this.borrowed = false;
  }
}

class Person{
  String name;
  String cpf;

  public Person(String name, String cpf){
    this.name = name;
    this.cpf = cpf;
  }
  
  public String getName(){
    return name;
  }

  public String getCpf(){
    return cpf;
  }

}

class Borrow{
  private Person person;
  private ArrayList<Book> borrowedBooks;

  public Borrow(Person person){
    this.person = person;
    borrowedBooks = new ArrayList<>();
  }

  public void borrowABook(Book book){
    if(!book.isBorrowed()){
      book.borrow();
      borrowedBooks.add(book);

      System.out.println("Livro \"" + book.title + "\" emprestado para " + person.getName());
    }else{
      System.out.println("Livro \"" + book.title + "\" ja foi emprestado ") ;
    }
  }

  public void giveBack(Book book){
    if(book.isBorrowed()){
      book.takeBack();
      borrowedBooks.remove(book);
      System.out.println("Livro \"" + book.title + "\" foi devolvido por " + person.getName());
    }else{
      System.out.println("Livro \"" + book.title + "\" nao foi emprestado para " + person.getName() + ", logo nao pode ser devolvido");
    }
  }

  public void listBorrowed(){
    System.out.println("\nLivros emprestados para " + person.getName() + ":");
    for (int i = 0; i < borrowedBooks.size(); i++) {
      System.out.println("- " + borrowedBooks.get(i).title);
    }
  }

  public void searchBook(String name) {
    for (int i = 0; i < borrowedBooks.size(); i++) {
      if (borrowedBooks.get(i).title.equalsIgnoreCase(name)) {
        System.out.println("Livro encontrado: " + borrowedBooks.get(i).title + " foi emprestado para " + person.getName());
        return;
      }
    }

    System.out.println("Livro nao encontrado");
  }

}

public class App {
    public static void main(String[] args) {
        Person person = new Person("Thiago", "345.904.903-90");

        Book livro1 = new Book("1984", "George Orwell");
        Book livro2 = new Book("Dom Casmurro", "Machado de Assis");

        Borrow emprestimo = new Borrow(person);

        emprestimo.borrowABook(livro1);
        emprestimo.borrowABook(livro2);

        emprestimo.listBorrowed();

        emprestimo.giveBack(livro1);

        emprestimo.listBorrowed();

        emprestimo.searchBook("Dom Casmurro");
    }
}

