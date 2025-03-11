class Person {
  public String name;
  public int age;
  public Person father;
  public Person mother;

  public Person(String name, int age, Person father, Person mother) {
    this.name = name;
    this.age = age;
    this.father = father;
    this.mother = mother;
  }

  public void showGenealogy() {
    System.out.println("\nNome: " + name);
    System.out.println("Idade: " + age);
    if (father != null) {
      System.out.println("Pai: " + father.name);
    }else{
      System.out.println("Desconhecido");
    }

    if (mother != null) {
      System.out.println("Mae: " + mother.name);
    }else{
      System.out.println("Desconhecida");
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

    Person me = new Person("Cleber", 32, father, mother);

    me.showGenealogy();
    father.showGenealogy();
    mother.showGenealogy();


  }
}
