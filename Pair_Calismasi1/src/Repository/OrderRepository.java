package Repository;
import Entity.Order;
import Entity.OrderItem;


import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IRepository<Order>{

    List<Order> orders = new ArrayList<>();


    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    @Override
    public void add(Order entity) {

        boolean result = this.orders.add(entity);
        if (result){
            System.out.println("Sipariş eklendi.");
        }else{
            System.out.println("Sipariş eklenirken hata oluştu!");
            System.out.println("Result: "+result);
        }


    }

    @Override
    public void delete(Order order) {
        boolean result = this.orders.remove(order);
        if (result){
            System.out.println("Sipariş silindi.");
        }else{
            System.out.println("Sipariş silinirken hata oluştu!");
            System.out.println("Result: "+result);
        };


    }

    @Override
    public void update(int id, Order newEntity) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                orders.set(i, newEntity);
                return;
            }
        }
        System.out.println("Bu id ye sahip entity bulunamadu");

    }

    @Override
    public Order getById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void infoOrderItems(Order order){
        for(OrderItem orderItem: order.getOrderItems()){
            System.out.println("----------------OrderItem----------------------");
            System.out.println(
                    "ProductId: "+orderItem.getProductId()+"\n" +
                            "Price: "    +orderItem.getPrice()+"\n"+
                            "Quantity: " +orderItem.getQuantity()+"\n"+
                            "Discount:"  +orderItem.getDiscount()
            );
            System.out.println("--------------------------------------");
        }
    }

    public void infoOrder(Order order){
        System.out.println("---------------Order-----------------------");
        System.out.println(
                "OrderId: "+order.getId()+"\n" +
                        "TotalPrice: "    +order.getTotalPrice()+"\n"+
                        "CustomerId: " +order.getCustomerId()+"\n"
        );
        System.out.println("--------------------------------------");
    }






}
