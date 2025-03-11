import java.util.Scanner;

abstract class GeometeryFigure {
  public abstract double calcArea();
}

class Square extends GeometeryFigure {
  private double side;

  public Square(double side) {
    this.side = side;
  }

  @Override
  public double calcArea() {
    return side * side;
  }
}

class Rectangle extends GeometeryFigure {
  private double side;
  private double height;

  public Rectangle(double side, double height) {
    this.side = side;
    this.height = height;
  }

  @Override
  public double calcArea() {
    return side * height;
  }

}

class Triangle extends GeometeryFigure {
  private double side;
  private double height;

  public Triangle(double side, double height) {
    this.side = side;
    this.height = height;
  }

  @Override
  public double calcArea() {
    return (side * height) / 2;
  }

}

class Circle extends GeometeryFigure {
  private double raio;

  public Circle(double raio) {
    this.raio = raio;
  }

  @Override
  public double calcArea() {
    return Math.PI * raio * raio;
  }

}

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    GeometeryFigure figure = null;

    System.out.println("Escolha uma figura geométrica:");
    System.out.println("1 - Quadrado");
    System.out.println("2 - Retângulo");
    System.out.println("3 - Triângulo");
    System.out.println("4 - Círculo");
    int choose = scanner.nextInt();

    switch (choose) {
      case 1:
        System.out.print("Digite o lado do quadrado: ");
        double side = scanner.nextDouble();
        figure = new Square(side);
        break;
      case 2:
        System.out.print("Digite a largura do retângulo: ");
        side = scanner.nextDouble();
        System.out.print("Digite a altura do retângulo: ");
        double height = scanner.nextDouble();
        figure = new Rectangle(side, height);
        break;
      case 3:
        System.out.print("Digite a base do triângulo: ");
        side = scanner.nextDouble();
        System.out.print("Digite a altura do triângulo: ");
        height = scanner.nextDouble();
        figure = new Triangle(side, height);
        break;
      case 4:
        System.out.print("Digite o raio do círculo: ");
        double raio = scanner.nextDouble();
        figure = new Circle(raio);
        break;
      default:
        System.out.println("Opção inválida!");
    }

    if (figure != null) {
      System.out.println("Área da figura: " + figure.calcArea());
    }

    scanner.close();

  }
}
