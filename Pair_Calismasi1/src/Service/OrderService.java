package Service;

import Entity.Order;
import Entity.OrderItem;
import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;

import java.util.List;

public class OrderService implements IService<Order>{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,ProductRepository productRepository,CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository=productRepository;
        this.customerRepository=customerRepository;
    }


    @Override
    public void add(Order entity) {

        orderRepository.add(entity);
        /*double totalPriceResult = totalPriceCalculation(entity);
        System.out.println("Siparişiniz için ödemeniz gereken tutar: "+totalPriceResult);
        orderRepository.getById(entity.getId()).setTotalPrice(totalPriceResult);*/
        customerRepository.addOrderToOrderList(entity.getCustomerId(),entity);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void update(int id, Order entity) {
        orderRepository.update(id,entity);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public Order getById(int id) {
        return orderRepository.getById(id);
    }

    public void addOrderItemToOrder(int orderId,OrderItem orderItem){
        boolean result = productRepository.stockControl(orderItem.getProductId(),orderItem.getQuantity());
        String output = result ? "Stockta yeterli ürün bulunmaktadır" : "Stockta yeterli ürün bulunmamaktadır.";
        if (!result){
            System.out.println(output);
        }
        if (result){
            double orderItemPrice = productRepository.getById(orderItem.getProductId()).getPrice();
            double orderItemDiscount = orderItemPrice*(orderItem.getDiscount()/100);
            orderItem.setPrice(orderItemPrice-orderItemDiscount);
            productRepository.reduceStock(orderItem.getProductId(), orderItem.getQuantity());
            System.out.println("Stoktan "+orderItem.getQuantity()+" adet düşülmüştür.");
            orderRepository.getById(orderId).addOrderItems(orderItem);
            double temp=getById(orderId).getTotalPrice()+productRepository.getById(orderItem.getProductId()).getPrice()* orderItem.getQuantity();
            getById(orderId).setTotalPrice(temp);
        }
    }





    public void infoOrderItems(Order order){
        orderRepository.infoOrderItems(order);
    }

    public void infoOrder(Order order){
        orderRepository.infoOrder(order);
    }

    public double TotalPrice(int orderId){
        return getById(orderId).getTotalPrice();
    }
}