import Entity.Customer;
import Entity.Order;
import Entity.OrderItem;
import Entity.Product;
import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Service.CustomerService;
import Service.OrderService;
import Service.ProductService;

public class Main {
    public static void main(String[] args) {
        //Servislerin üretilmesi
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository,productRepository,customerRepository);

        //Customer oluşturulması
        Customer customer1 = new Customer(1,"ali","ali2@gmail.com","123456789");
        customerService.add(customer1);

        // Ürün oluşturulması
        Product product1 = new Product(1,"Pc",3000,30);
        Product product2 = new Product(2,"Tv",2000,30);
        Product product3 = new Product(3,"iPhone",1000,30);
        Product product4 = new Product(4,"Samsung",500,30);
        Product product5 = new Product(5,"Xiaomi",400,30);
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
        productService.add(product5);

        //OrderItem nesnelerinin oluşturulması
        OrderItem orderItem1 = new OrderItem(1,5);
        OrderItem orderItem2 = new OrderItem(2,5);
        OrderItem orderItem3 = new OrderItem(3,5);
        OrderItem orderItem4 = new OrderItem(4,5);


        //Order nesnesinin oluşturulması
        Order order1 = new Order(1,1);
        orderService.add(order1);
        orderService.addOrderItemToOrder(order1.getId(),orderItem1);
        orderService.addOrderItemToOrder(order1.getId(),orderItem2);
        orderService.addOrderItemToOrder(order1.getId(),orderItem3);
        orderService.addOrderItemToOrder(order1.getId(),orderItem4);

        //Orderın tutarı
        System.out.println("Order total: "+orderService.TotalPrice(order1.getId()));


        //Güncel product bilgileri
        productService.infoProduct(product1.getId());
        productService.infoProduct(product2.getId());
        productService.infoProduct(product3.getId());
        productService.infoProduct(product4.getId());

        //Güncel product bilgileri
        orderService.infoOrderItems(order1);

        //Güncel order bilgileri
        orderService.infoOrder(order1);

        //güncel customer bilgileri
        customerService.infoCustomer(customer1.getId());












    }
}