import java.util.ArrayList;

class Product {
  private String name;
  private double price;
  private int stock;

  public Product(String name, double price, int qtd) {
    this.name = name;
    this.price = price;
    this.stock = qtd;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public boolean reduceQtd(int qtd) {
    if (qtd > stock) {
      System.out.println("Estoque insuficiente para o produto: " + name);
      return false;
    } else {
      stock -= qtd;
      return true;
    }
  }

  @Override
  public String toString() {
    return name + " - R$ " + price + " (Estoque: " + stock + ")";
  }

}

class OrderItem {
  private Product product;
  private int qtd;

  public OrderItem(Product product, int qtd) {
    if (product.reduceQtd(qtd)) {
      this.product = product;
      this.qtd = qtd;
    }
  }

  public double calcTotal() {
    return product.getPrice() * qtd;
  }

  @Override
  public String toString() {
    return qtd + " un. " + product.getName() + " - Total: R$ " + calcTotal();
  }

}

enum Payment {
  DINHEIRO, CHEQUE, CARTAO;
}

class Order {
  private ArrayList<OrderItem> item;
  private Payment payment;

  public Order(Payment payment) {
    this.item = new ArrayList<>();
    this.payment = payment;
  }

  public void addItem(Product product, int qtd) {
    item.add(new OrderItem(product, qtd));
  }

  public double totalOrder() {
    double total = 0;

    for (int i = 0; i < item.size(); i++) {
      total += item.get(i).calcTotal();
    }

    return total;
  }

  @Override
  public String toString() {
    StringBuilder stringForm = new StringBuilder();
    stringForm.append("Pedido: \n");

    for (int i = 0; i < item.size(); i++) {
      stringForm.append(item.get(i)).append("\n");
    }
    stringForm.append("Forma de pagamento: ").append(payment).append("\n");
    stringForm.append("Total do pedido: R$ ").append(totalOrder());
    return stringForm.toString();
  }
}

public class App {
  public static void main(String[] args) {

    Product pasta = new Product("Macarrao", 5.50, 10);
    Product potatoChips = new Product("Chips de batata", 12.00, 3);

    Order order = new Order(Payment.DINHEIRO);

    order.addItem(pasta, 2);
    order.addItem(potatoChips, 3);

    System.out.println(order);
  }
}
