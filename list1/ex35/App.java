import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Person {
  public String name;
  public int age;
  public Person father;
  public Person mother;
  private Set<Person> children;

  public Person(String name, int age, Person father, Person mother) {
    this.name = name;
    this.age = age;
    this.father = father;
    this.mother = mother;
    this.children = new HashSet<>();
  }

  public void addSon(Person son){
    children.add(son);
  }

  public void showGenealogy() {
    System.out.println("\nNome: " + name);
    System.out.println("Idade: " + age);
    if (father != null) {
      System.out.println("Pai: " + father.name);
    } else {
      System.out.println("Desconhecido");
    }

    if (mother != null) {
      System.out.println("Mae: " + mother.name);
    } else {
      System.out.println("Desconhecida");
    }

    if (!children.isEmpty()) {
      System.out.println("Filhos: ");
      Iterator<Person> iterator = children.iterator();

      while (iterator.hasNext()) {
        Person child = iterator.next();
        System.out.println("- " + child.name);
      }
    } else {
      System.out.println("Sem filhos!");
    }

  }

}

public class App {
  public static void main(String[] args) {
    Person grandMother_father = new Person("Angelina", 90, null, null);
    Person grandFather_father = new Person("Robson", 92, null, null);

    Person grandMother_mother = new Person("Janaina", 90, null, null);
    Person grandFather_mother = new Person("Cleberson", 92, null, null);

    Person father = new Person("Gabriel", 63, grandFather_father, grandMother_father);
    Person mother = new Person("Claudia", 60, grandFather_mother, grandMother_mother);

    Person me = new Person("Julio", 33, father, mother);

    grandMother_mother.addSon(mother);
    grandFather_mother.addSon(mother);

    grandMother_father.addSon(father);
    grandFather_father.addSon(father);

    father.addSon(me);
    mother.addSon(me);

    me.showGenealogy();
    father.showGenealogy();
    grandFather_father.showGenealogy();

     
  }
}
