import java.util.ArrayList;
import java.util.Scanner;

class Product{
    String name;
    int id;
    double price;
    int quantity;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
class Inventory {
    static ArrayList<Product> productList = new ArrayList<>();
   
    static void addProduct(int id, String name, double price, int quantity) {
        productList.add(new Product(id, name, price, quantity));
        System.out.println(" Product added successfully!");
    }
   
    static void viewInventory() {
        System.out.println("\n Inventory List:");
        for (Product p : productList) {
            System.out.println("ID: " + p.id + " | Product Name: " + p.name + " | Price: P" + p.price + " | Stock: " + p.quantity);
        }
    }
   
    static Product findProduct(int id) {
        for (Product p : productList) {
            if (p.id == id) return p;
        }
        return null;
    }
}

class CustomerOrder {
    String customerName;
    String productName;
    int quantity;
    double totalCost;

    CustomerOrder(String customerName, String productName, int quantity, double totalCost) {
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }
}

class Order {
    static int totalOrders = 0;
    static ArrayList<CustomerOrder> orderList = new ArrayList<>();

    static void placeOrder(String customerName, int productId, int qty) {
        Product p = Inventory.findProduct(productId);
        if (p == null) {
            System.out.println(" Product not found.");
        } else if (p.quantity < qty) {
            System.out.println(" Not enough stock.");
        } else {
            p.quantity -= qty;
            totalOrders++;
            double cost = p.price * qty;
            orderList.add(new CustomerOrder(customerName, p.name, qty, cost));
            System.out.println(" Order placed! Total cost: P" + cost);
        }
    }
   static void viewAllOrders() {
    System.out.println("\n Order List:");
    if (orderList.isEmpty()) {
        System.out.println(" No orders have been placed yet.");
    } else {
        for (CustomerOrder o : orderList) {
            System.out.println("Customer: " + o.customerName +
                               " | Product: " + o.productName +
                               " | Quantity: " + o.quantity +
                               " | Total: P" + o.totalCost);
        }
    }
}
}
class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

public class BusinessApp {
    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n Main Menu:");
            System.out.println("1. View Inventory");
            System.out.println("2. Place Order");
            System.out.println("3. Add New Product");
            System.out.println("4. Remove Product");
            System.out.println("5. Customer's Order");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Inventory.viewInventory();
                    break;
                case 2:
                  System.out.print("Enter Customer Name: ");
                  sc.nextLine(); 

                  String customerName = sc.nextLine();
                  System.out.print("Enter Product ID: ");
                  int id = sc.nextInt();

                  System.out.print("Enter Quantity: ");
                  int qty = sc.nextInt();
                  Order.placeOrder(customerName, id, qty);            
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    int newId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int stock = sc.nextInt();
                    Inventory.addProduct(newId, name, price, stock);
                    break;
                case 4:
                    if (Inventory.productList.isEmpty()) {
                    System.out.println("No records found");
                    } else {
                    Inventory.viewInventory(); 
                    System.out.print("Enter number to remove: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    if (index > 0 && index <= Inventory.productList.size()) {
                    Product removed = Inventory.productList.remove(index - 1);
                    System.out.println("Removed: " + removed.name);
                    } else {
                    System.out.println("Record Invalid");
                    }
                    }
                    break;  
                case 5:
                   Order.viewAllOrders();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Program");                       
                    Thread.sleep(1000);
                    System.out.println(". . .");
                    Thread.sleep(2000);
                    System.out.println("Total orders placed: " + Order.totalOrders);
                    break;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}